class DummyClass_91486 {
    @TestLogging("_root:DEBUG, org.elasticsearch.transport:TRACE")
    public void testCloseWhileConcurrentlyConnecting() throws IOException, InterruptedException, BrokenBarrierException {
        List<DiscoveryNode> knownNodes = new CopyOnWriteArrayList<>();
        try (MockTransportService seedTransport = startTransport("seed_node", knownNodes, Version.CURRENT);
             MockTransportService seedTransport1 = startTransport("seed_node_1", knownNodes, Version.CURRENT);
             MockTransportService discoverableTransport = startTransport("discoverable_node", knownNodes, Version.CURRENT)) {
            DiscoveryNode seedNode = seedTransport.getLocalDiscoNode();
            DiscoveryNode seedNode1 = seedTransport1.getLocalDiscoNode();
            knownNodes.add(seedTransport.getLocalDiscoNode());
            knownNodes.add(discoverableTransport.getLocalDiscoNode());
            knownNodes.add(seedTransport1.getLocalDiscoNode());
            Collections.shuffle(knownNodes, random());
            List<Supplier<DiscoveryNode>> seedNodes = Arrays.asList(() -> seedNode1, () -> seedNode);
            Collections.shuffle(seedNodes, random());

            try (MockTransportService service = MockTransportService.createNewService(Settings.EMPTY, Version.CURRENT, threadPool, null)) {
                service.start();
                service.acceptIncomingRequests();
                try (RemoteClusterConnection connection = new RemoteClusterConnection(Settings.EMPTY, "test-cluster",
                    seedNodes, service, service.getConnectionManager(), Integer.MAX_VALUE, n -> true)) {
                    int numThreads = randomIntBetween(4, 10);
                    Thread[] threads = new Thread[numThreads];
                    CyclicBarrier barrier = new CyclicBarrier(numThreads + 1);
                    for (int i = 0; i < threads.length; i++) {
                        final int numConnectionAttempts = randomIntBetween(10, 100);
                        threads[i] = new Thread() {
                            @Override
                            public void run() {
                                try {
                                    barrier.await();
                                    CountDownLatch latch = new CountDownLatch(numConnectionAttempts);
                                    for (int i = 0; i < numConnectionAttempts; i++) {
                                        AtomicReference<Exception> executed = new AtomicReference<>();
                                        ActionListener<Void> listener = ActionListener.wrap(
                                            x -> {
                                                if (executed.compareAndSet(null, new RuntimeException())) {
                                                    latch.countDown();
                                                } else {
                                                    throw new AssertionError("shit's been called twice", executed.get());
                                                }
                                            },
                                            x -> {
                                                if (executed.compareAndSet(null, x)) {
                                                    latch.countDown();
                                                } else {
                                                    final String message = x.getMessage();
                                                    if ((executed.get().getClass() == x.getClass()
                                                        && "operation was cancelled reason [connect handler is closed]".equals(message)
                                                        && message.equals(executed.get().getMessage())) == false) {
                                                        // we do cancel the operation and that means that if timing allows it, the caller
                                                        // of a blocking call as well as the handler will get the exception from the
                                                        // ExecutionCancelledException concurrently. unless that is the case we fail
                                                        // if we get called more than once!
                                                        AssertionError assertionError = new AssertionError("shit's been called twice", x);
                                                        assertionError.addSuppressed(executed.get());
                                                        throw assertionError;
                                                    }
                                                }
                                                if (x instanceof RejectedExecutionException || x instanceof AlreadyClosedException
                                                    || x instanceof CancellableThreads.ExecutionCancelledException) {
                                                    // that's fine
                                                } else {
                                                    throw new AssertionError(x);
                                                }
                                            });
                                        try {
                                            connection.updateSeedNodes(null, seedNodes, listener);
                                        } catch (Exception e) {
                                            // it's ok if we're shutting down
                                            assertThat(e.getMessage(), containsString("threadcontext is already closed"));
                                            latch.countDown();
                                        }
                                    }
                                    latch.await();
                                } catch (Exception ex) {
                                    throw new AssertionError(ex);
                                }
                            }

}