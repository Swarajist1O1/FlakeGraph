class DummyClass_70785 {
    @Test
    public void shouldReturnTrueWhenAwaitingForStartAndStopToComplete() throws Throwable {
        latch = new StartAndStopLatch(1, 1, this::complete, dependents, clock);
        future = asyncAwait(100);
        latch.recordStart();
        latch.recordStop();
        clock.sleep(10);
        assertTrue(future.get(200, TimeUnit.MILLISECONDS));
        assertTrue(future.isDone());
    }

}