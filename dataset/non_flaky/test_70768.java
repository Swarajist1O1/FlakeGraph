class DummyClass_70768 {
    @Test
    public void shouldFailToWaitForRestartThatNeverHappens() throws Exception {
        waiters = Executors.newSingleThreadExecutor();

        latch = counter.expectedRestarts(1);
        Future<Boolean> future = asyncAwait(100, TimeUnit.MILLISECONDS);

        clock.sleep(1000);
        // Record a stop but NOT a start
        counter.recordStop();
        assertFalse(future.get(200, TimeUnit.MILLISECONDS));
        assertTrue(future.isDone());
    }

}