class DummyClass_70783 {
    @Test
    public void shouldReturnFalseWhenAwaitingForStartToNeverComplete() throws Throwable {
        latch = new StartAndStopLatch(1, 1, this::complete, dependents, clock);
        future = asyncAwait(100);
        clock.sleep(10);
        assertFalse(future.get(200, TimeUnit.MILLISECONDS));
        assertTrue(future.isDone());
    }

}