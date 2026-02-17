class DummyClass_70787 {
    @Test
    public void shouldReturnTrueWhenAwaitingForStartAndStopAndDependentLatch() throws Throwable {
        StartAndStopLatch depLatch = new StartAndStopLatch(1, 1, this::complete, null, clock);
        dependents = Collections.singletonList(depLatch);
        latch = new StartAndStopLatch(1, 1, this::complete, dependents, clock);

        future = asyncAwait(100);
        latch.recordStart();
        latch.recordStop();
        depLatch.recordStart();
        depLatch.recordStop();
        clock.sleep(10);
        assertTrue(future.get(200, TimeUnit.MILLISECONDS));
        assertTrue(future.isDone());
    }

}