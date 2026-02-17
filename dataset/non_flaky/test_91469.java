class DummyClass_91469 {
    @TestLogging("_root:DEBUG,org.elasticsearch.action.bulk:TRACE,org.elasticsearch.action.get:TRACE," +
    public void testAckedIndexing() throws Exception {

        final int seconds = !(TEST_NIGHTLY && rarely()) ? 1 : 5;
        final String timeout = seconds + "s";

        final List<String> nodes = startCluster(rarely() ? 5 : 3);

        assertAcked(prepareCreate("test")
            .setSettings(Settings.builder()
                .put(IndexMetaData.SETTING_NUMBER_OF_SHARDS, 1 + randomInt(2))
                .put(IndexMetaData.SETTING_NUMBER_OF_REPLICAS, randomInt(2))
            ));
        ensureGreen();

        ServiceDisruptionScheme disruptionScheme = addRandomDisruptionScheme();
        logger.info("disruption scheme [{}] added", disruptionScheme);

        final ConcurrentHashMap<String, String> ackedDocs = new ConcurrentHashMap<>(); // id -> node sent.

        final AtomicBoolean stop = new AtomicBoolean(false);
        List<Thread> indexers = new ArrayList<>(nodes.size());
        List<Semaphore> semaphores = new ArrayList<>(nodes.size());
        final AtomicInteger idGenerator = new AtomicInteger(0);
        final AtomicReference<CountDownLatch> countDownLatchRef = new AtomicReference<>();
        final List<Exception> exceptedExceptions = Collections.synchronizedList(new ArrayList<Exception>());

        logger.info("starting indexers");
        try {
            for (final String node : nodes) {
                final Semaphore semaphore = new Semaphore(0);
                semaphores.add(semaphore);
                final Client client = client(node);
                final String name = "indexer_" + indexers.size();
                final int numPrimaries = getNumShards("test").numPrimaries;
                Thread thread = new Thread(() -> {
                    while (!stop.get()) {
                        String id = null;
                        try {
                            if (!semaphore.tryAcquire(10, TimeUnit.SECONDS)) {
                                continue;
                            }
                            logger.info("[{}] Acquired semaphore and it has {} permits left", name, semaphore.availablePermits());
                            try {
                                id = Integer.toString(idGenerator.incrementAndGet());
                                int shard = Math.floorMod(Murmur3HashFunction.hash(id), numPrimaries);
                                logger.trace("[{}] indexing id [{}] through node [{}] targeting shard [{}]", name, id, node, shard);
                                IndexResponse response =
                                        client.prepareIndex("test", "type", id)
                                                .setSource("{}", XContentType.JSON)
                                                .setTimeout(timeout)
                                                .get(timeout);
                                assertEquals(DocWriteResponse.Result.CREATED, response.getResult());
                                ackedDocs.put(id, node);
                                logger.trace("[{}] indexed id [{}] through node [{}], response [{}]", name, id, node, response);
                            } catch (ElasticsearchException e) {
                                exceptedExceptions.add(e);
                                final String docId = id;
                                logger.trace(() -> new ParameterizedMessage("[{}] failed id [{}] through node [{}]", name, docId, node), e);
                            } finally {
                                countDownLatchRef.get().countDown();
                                logger.trace("[{}] decreased counter : {}", name, countDownLatchRef.get().getCount());
                            }
                        } catch (InterruptedException e) {
                            // fine - semaphore interrupt
                        } catch (AssertionError | Exception e) {
                            logger.info(() -> new ParameterizedMessage("unexpected exception in background thread of [{}]", node), e);
                        }
                    }
                });

                thread.setName(name);
                thread.start();
                indexers.add(thread);
            }

            int docsPerIndexer = randomInt(3);
            logger.info("indexing {} docs per indexer before partition", docsPerIndexer);
            countDownLatchRef.set(new CountDownLatch(docsPerIndexer * indexers.size()));
            for (Semaphore semaphore : semaphores) {
                semaphore.release(docsPerIndexer);
            }
            assertTrue(countDownLatchRef.get().await(1, TimeUnit.MINUTES));

            for (int iter = 1 + randomInt(2); iter > 0; iter--) {
                logger.info("starting disruptions & indexing (iteration [{}])", iter);
                disruptionScheme.startDisrupting();

                docsPerIndexer = 1 + randomInt(5);
                logger.info("indexing {} docs per indexer during partition", docsPerIndexer);
                countDownLatchRef.set(new CountDownLatch(docsPerIndexer * indexers.size()));
                Collections.shuffle(semaphores, random());
                for (Semaphore semaphore : semaphores) {
                    assertThat(semaphore.availablePermits(), equalTo(0));
                    semaphore.release(docsPerIndexer);
                }
                logger.info("waiting for indexing requests to complete");
                assertTrue(countDownLatchRef.get().await(docsPerIndexer * seconds * 1000 + 2000, TimeUnit.MILLISECONDS));

                logger.info("stopping disruption");
                disruptionScheme.stopDisrupting();
                for (String node : internalCluster().getNodeNames()) {
                    ensureStableCluster(nodes.size(), TimeValue.timeValueMillis(disruptionScheme.expectedTimeToHeal().millis() +
                        DISRUPTION_HEALING_OVERHEAD.millis()), true, node);
                }
                // in case of a bridge partition, shard allocation can fail "index.allocation.max_retries" times if the master
                // is the super-connected node and recovery source and target are on opposite sides of the bridge
                if (disruptionScheme instanceof NetworkDisruption &&
                    ((NetworkDisruption) disruptionScheme).getDisruptedLinks() instanceof Bridge) {
                    assertAcked(client().admin().cluster().prepareReroute().setRetryFailed(true));
                }
                ensureGreen("test");

                logger.info("validating successful docs");
                assertBusy(() -> {
                    for (String node : nodes) {
                        try {
                            logger.debug("validating through node [{}] ([{}] acked docs)", node, ackedDocs.size());
                            for (String id : ackedDocs.keySet()) {
                                assertTrue("doc [" + id + "] indexed via node [" + ackedDocs.get(id) + "] not found",
                                    client(node).prepareGet("test", "type", id).setPreference("_local").get().isExists());
                            }
                        } catch (AssertionError | NoShardAvailableActionException e) {
                            throw new AssertionError(e.getMessage() + " (checked via node [" + node + "]", e);
                        }
                    }
                }, 30, TimeUnit.SECONDS);

                logger.info("done validating (iteration [{}])", iter);
            }
        } finally {
            if (exceptedExceptions.size() > 0) {
                StringBuilder sb = new StringBuilder();
                for (Exception e : exceptedExceptions) {
                    sb.append("\n").append(e.getMessage());
                }
                logger.debug("Indexing exceptions during disruption: {}", sb);
            }
            logger.info("shutting down indexers");
            stop.set(true);
            for (Thread indexer : indexers) {
                indexer.interrupt();
                indexer.join(60000);
            }
        }
    }

}