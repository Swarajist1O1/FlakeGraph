class DummyClass_13846 {
    @Test
    public void shouldBuildUpGracefullyWhilePassingMinPoolSizeBeforeTimerRings() throws InterruptedException
    {
        // GIVEN
        StatefulMonitor stateMonitor = new StatefulMonitor();
        FakeClock clock = new FakeClock();
        final ResourcePool<Something> pool = getResourcePool( stateMonitor, clock, 5 );

        // WHEN
        acquireFromPool( pool, 15 );

        // THEN
        assertEquals( -1, stateMonitor.currentPeakSize.get() );
        assertEquals( 15, stateMonitor.created.get() );
        assertEquals( -1, stateMonitor.targetSize.get() );
        assertEquals( 0, stateMonitor.disposed.get() );
    }

}