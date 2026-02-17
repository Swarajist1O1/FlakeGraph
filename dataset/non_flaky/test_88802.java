class DummyClass_88802 {
    @Test
    public void testMultithreading() throws Exception {
        final int THREAD_CNT = 8;
        final int ITERATION_CNT = 20;
        final int BATCH_SIZE = 1000;
        final int PAGE_CNT = 3;

        IgniteConfiguration srvCfg = Config.getServerConfiguration();

        // No peer class loading from thin clients: we need the server to know about this class to deserialize
        // ScanQuery filter.
        srvCfg.setBinaryConfiguration(new BinaryConfiguration().setTypeConfigurations(Arrays.asList(
            new BinaryTypeConfiguration(getClass().getName()),
            new BinaryTypeConfiguration(SerializedLambda.class.getName())
        )));

        try (Ignite ignored = Ignition.start(srvCfg);
             IgniteClient client = Ignition.startClient(new ClientConfiguration().setAddresses(Config.SERVER))
        ) {
            ClientCache<Integer, String> cache = client.createCache("testMultithreading");

            AtomicInteger cnt = new AtomicInteger(1);

            AtomicReference<Throwable> error = new AtomicReference<>();

            Runnable assertion = () -> {
                try {
                    int rangeStart = cnt.getAndAdd(BATCH_SIZE);
                    int rangeEnd = rangeStart + BATCH_SIZE;

                    Map<Integer, String> data = IntStream.range(rangeStart, rangeEnd).boxed()
                        .collect(Collectors.toMap(i -> i, i -> String.format("String %s", i)));

                    cache.putAll(data);

                    Query<Cache.Entry<Integer, String>> qry = new ScanQuery<Integer, String>()
                        .setPageSize(data.size() / PAGE_CNT)
                        .setFilter((i, s) -> i >= rangeStart && i < rangeEnd);

                    try (QueryCursor<Cache.Entry<Integer, String>> cur = cache.query(qry)) {
                        List<Cache.Entry<Integer, String>> res = cur.getAll();

                        assertEquals("Unexpected number of entries", data.size(), res.size());

                        Map<Integer, String> act = res.stream()
                            .collect(Collectors.toMap(Cache.Entry::getKey, Cache.Entry::getValue));

                        assertEquals("Unexpected entries", data, act);
                    }
                }
                catch (Throwable ex) {
                    error.set(ex);
                }
            };

            CountDownLatch complete = new CountDownLatch(THREAD_CNT);

            Runnable manyAssertions = () -> {
                for (int i = 0; i < ITERATION_CNT && error.get() == null; i++)
                    assertion.run();

                complete.countDown();
            };

            ExecutorService threadPool = Executors.newFixedThreadPool(THREAD_CNT);

            IntStream.range(0, THREAD_CNT).forEach(t -> threadPool.submit(manyAssertions));

            assertTrue("Timeout", complete.await(180, TimeUnit.SECONDS));

            String errMsg = error.get() == null ? "" : error.get().getMessage();

            assertNull(errMsg, error.get());
        }
    }

}