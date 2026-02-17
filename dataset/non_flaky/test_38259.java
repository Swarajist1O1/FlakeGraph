class DummyClass_38259 {
    @Test(expected=TransactionFailedRetriableException.class)
    public void testConcurrentWriteSkew() throws InterruptedException, BrokenBarrierException {
        Transaction t0 = startTransaction();
        put(t0, "row1", "col1", "100");
        put(t0, "row2", "col1", "100");
        t0.commit();

        final CyclicBarrier barrier = new CyclicBarrier(2);

        final Transaction t1 = startTransaction();
        ExecutorService exec = PTExecutors.newCachedThreadPool();
        Future<?> f = exec.submit( new Callable<Void>() {
            @Override
            public Void call() throws Exception {
                withdrawMoney(t1, true, false);
                barrier.await();
                t1.commit();
                return null;
            }

}