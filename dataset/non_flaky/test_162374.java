class DummyClass_162374 {
    @Test(timeout = 5_000)
    public void testThreadSafety() throws Exception {
        final int numOfThreads = 3;
        CountDownLatch latch = new CountDownLatch(numOfThreads);
        AtomicInteger counter = new AtomicInteger();

        Future<Integer> lazyFuture = new LazyFuture<Integer>() {
            @Override
            @SneakyThrows(InterruptedException.class)
            protected Integer resolve() {
                latch.await();
                return counter.incrementAndGet();
            }
        };

        Future<List<Integer>> task = new ForkJoinPool(numOfThreads).submit(() -> {
            return IntStream.rangeClosed(1, numOfThreads).parallel().mapToObj(i -> Futures.getUnchecked(lazyFuture)).collect(toList());
        });

        while (latch.getCount() > 0) {
            latch.countDown();
        }

        assertEquals("All threads receives the same result", Collections.nCopies(numOfThreads, 1), task.get());
    }

}