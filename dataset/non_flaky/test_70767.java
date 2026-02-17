class DummyClass_70767 {
    @Test
    public void shouldExpectRestarts() throws Exception {
        waiters = Executors.newSingleThreadExecutor();

        latch = counter.expectedRestarts(1);
        Future<Boolean> future = asyncAwait(100, TimeUnit.MILLISECONDS);

        clock.sleep(1000);
        counter.recordStop();
        counter.recordStart();
        assertTrue(future.get(200, TimeUnit.MILLISECONDS));
        assertTrue(future.isDone());
    }

}