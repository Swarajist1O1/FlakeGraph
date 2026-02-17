class DummyClass_91482 {
    @TestLogging("org.elasticsearch.common.util.concurrent:DEBUG")
    public void testAutoQueueSizingWithMax() throws Exception {
        ThreadContext context = new ThreadContext(Settings.EMPTY);
        ResizableBlockingQueue<Runnable> queue =
                new ResizableBlockingQueue<>(ConcurrentCollections.<Runnable>newBlockingQueue(),
                        5000);

        int threads = randomIntBetween(1, 5);
        int measureWindow = randomIntBetween(10, 100);
        int max = randomIntBetween(5010, 5024);
        logger.info("--> auto-queue with a measurement window of {} tasks", measureWindow);
        QueueResizingEsThreadPoolExecutor executor =
                new QueueResizingEsThreadPoolExecutor(
                        "test-threadpool", threads, threads, 1000,
                        TimeUnit.MILLISECONDS, queue, 10, max, fastWrapper(), measureWindow, TimeValue.timeValueMillis(1),
                        EsExecutors.daemonThreadFactory("queuetest"), new EsAbortPolicy(), context);
        executor.prestartAllCoreThreads();
        logger.info("--> executor: {}", executor);

        // Execute a task multiple times that takes 1ms
        executeTask(executor, measureWindow * 3);

        // The queue capacity should increase, but no higher than the maximum
        assertBusy(() -> {
            assertThat(queue.capacity(), equalTo(max));
        });
        executor.shutdown();
        executor.awaitTermination(10, TimeUnit.SECONDS);
        context.close();
    }

}