class DummyClass_91449 {
    @TestLogging("org.elasticsearch.index.shard:TRACE,org.elasticsearch.index.engine:TRACE")
    public void testStressMaybeFlushOrRollTranslogGeneration() throws Exception {
        createIndex("test");
        ensureGreen();
        IndicesService indicesService = getInstanceFromNode(IndicesService.class);
        IndexService test = indicesService.indexService(resolveIndex("test"));
        final IndexShard shard = test.getShardOrNull(0);
        assertFalse(shard.shouldPeriodicallyFlush());
        final boolean flush = randomBoolean();
        final Settings settings;
        if (flush) {
            // size of the operation plus two generations of overhead.
            settings = Settings.builder().put("index.translog.flush_threshold_size", "180b").build();
        } else {
            // size of the operation plus header and footer
            settings = Settings.builder().put("index.translog.generation_threshold_size", "117b").build();
        }
        client().admin().indices().prepareUpdateSettings("test").setSettings(settings).get();
        client().prepareIndex("test", "test", "0")
                .setSource("{}", XContentType.JSON)
                .setRefreshPolicy(randomBoolean() ? IMMEDIATE : NONE)
                .get();
        assertFalse(shard.shouldPeriodicallyFlush());
        final AtomicBoolean running = new AtomicBoolean(true);
        final int numThreads = randomIntBetween(2, 4);
        final Thread[] threads = new Thread[numThreads];
        final CyclicBarrier barrier = new CyclicBarrier(numThreads + 1);
        for (int i = 0; i < threads.length; i++) {
            threads[i] = new Thread(() -> {
                try {
                    barrier.await();
                } catch (final InterruptedException | BrokenBarrierException e) {
                    throw new RuntimeException(e);
                }
                while (running.get()) {
                    shard.afterWriteOperation();
                }
            });
            threads[i].start();
        }
        barrier.await();
        final CheckedRunnable<Exception> check;
        if (flush) {
            final FlushStats initialStats = shard.flushStats();
            client().prepareIndex("test", "test", "1").setSource("{}", XContentType.JSON).get();
            check = () -> {
                final FlushStats currentStats = shard.flushStats();
                String msg = String.format(Locale.ROOT, "flush stats: total=[%d vs %d], periodic=[%d vs %d]",
                    initialStats.getTotal(), currentStats.getTotal(), initialStats.getPeriodic(), currentStats.getPeriodic());
                assertThat(msg, currentStats.getPeriodic(), equalTo(initialStats.getPeriodic() + 1));
                assertThat(msg, currentStats.getTotal(), equalTo(initialStats.getTotal() + 1));
            };
        } else {
            final long generation = getTranslog(shard).currentFileGeneration();
            client().prepareIndex("test", "test", "1").setSource("{}", XContentType.JSON).get();
            check = () -> assertEquals(
                    generation + 1,
                    getTranslog(shard).currentFileGeneration());
        }
        assertBusy(check);
        running.set(false);
        for (int i = 0; i < threads.length; i++) {
            threads[i].join();
        }
        check.run();
    }

}