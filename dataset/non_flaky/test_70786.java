class DummyClass_70786 {
    @Test
    public void shouldReturnFalseWhenAwaitingForDependentLatchToComplete() throws Throwable {
        StartAndStopLatch depLatch = new StartAndStopLatch(1, 1, this::complete, null, clock);
        dependents = Collections.singletonList(depLatch);
        latch = new StartAndStopLatch(1, 1, this::complete, dependents, clock);

        future = asyncAwait(100);
        latch.recordStart();
        latch.recordStop();
        clock.sleep(10);
        assertFalse(future.get(200, TimeUnit.MILLISECONDS));
        assertTrue(future.isDone());
    }

}