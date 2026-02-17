class DummyClass_13845 {
    @Test
    public void shouldBuildUpGracefullyUntilReachedMinPoolSize() throws InterruptedException
    {
        // GIVEN
        StatefulMonitor stateMonitor = new StatefulMonitor();
        FakeClock clock = new FakeClock();
        final ResourcePool<Something> pool = getResourcePool( stateMonitor, clock, 5 );

        // WHEN
        acquireFromPool( pool, 5 );

        // THEN
        assertEquals( -1, stateMonitor.currentPeakSize.get() );
        assertEquals( -1, stateMonitor.targetSize.get() ); // that means the target size was not updated
        assertEquals( 0, stateMonitor.disposed.get() ); // no disposed happened, since the count to update is 10
    }

}