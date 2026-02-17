class DummyClass_70784 {
    @Test
    public void shouldReturnFalseWhenAwaitingForStopToNeverComplete() throws Throwable {
        latch = new StartAndStopLatch(1, 1, this::complete, dependents, clock);
        future = asyncAwait(100);
        latch.recordStart();
        clock.sleep(10);
        assertFalse(future.get(200, TimeUnit.MILLISECONDS));
        assertTrue(future.isDone());
    }

}