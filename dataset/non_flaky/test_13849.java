class DummyClass_13849 {
    @Test
    public void shouldSlowlyReduceTheNumberOfResourcesInThePoolWhenResourcesAreReleased() throws Exception
    {
        // given
        final int MIN_SIZE = 50;
        final int MAX_SIZE = 200;

        StatefulMonitor stateMonitor = new StatefulMonitor();
        FakeClock clock = new FakeClock();
        final ResourcePool<Something> pool = getResourcePool( stateMonitor, clock, MIN_SIZE );
        List<ResourceHolder> holders = new LinkedList<ResourceHolder>();

        buildAPeakOfAcquiredResourcesAndTriggerAlarmWithSideEffects( MAX_SIZE, clock, pool, holders );

        // when
        // After the peak, stay below MIN_SIZE concurrent usage, using up all already present resources.
        clock.forward( 110, TimeUnit.MILLISECONDS );
        for ( int i = 0; i < MAX_SIZE; i++ )
        {
            acquireFromPool( pool, 1 ).get( 0 ).release();
        }

        // then

        // currentPeakSize must have reset from the latest alarm to MIN_SIZE.
        assertEquals( 1, stateMonitor.currentPeakSize.get() ); // Alarm
        // targetSize must be set to MIN_SIZE since currentPeakSize was that 2 alarms ago and didn't increase
        assertEquals( MIN_SIZE, stateMonitor.targetSize.get() );
        // Only pooled resources must be used, disposing what is in excess
        // +1 for the alarm from buildAPeakOfAcquiredResourcesAndTriggerAlarmWithSideEffects
        assertEquals( MAX_SIZE - MIN_SIZE + 1, stateMonitor.disposed.get() );
    }

}