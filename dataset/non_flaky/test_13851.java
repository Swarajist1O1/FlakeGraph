class DummyClass_13851 {
    @Test
    public void shouldReclaimAndRecreateWhenLullBetweenSpikesOccurs() throws Exception
    {
        // given
        final int MIN_SIZE = 50;
        final int BELOW_MIN_SIZE = MIN_SIZE / 5;
        final int MAX_SIZE = 200;

        StatefulMonitor stateMonitor = new StatefulMonitor();
        FakeClock clock = new FakeClock();
        final ResourcePool<Something> pool = getResourcePool( stateMonitor, clock, MIN_SIZE );
        List<ResourceHolder> holders = new LinkedList<ResourceHolder>();

        buildAPeakOfAcquiredResourcesAndTriggerAlarmWithSideEffects( MAX_SIZE, clock, pool, holders );

        // when
        // After the peak, stay well below concurrent usage, using up all already present resources in the process
        clock.forward( 110, TimeUnit.MILLISECONDS );
        // Requires some rounds to happen, since there is constant racing between releasing and acquiring which does
        // not always result in reaping of resources, as there is reuse
        for ( int i = 0; i < 30; i++ )
        {
            // The latch is necessary to reduce races between batches
            CountDownLatch release = new CountDownLatch( BELOW_MIN_SIZE );
            for ( ResourceHolder holder : acquireFromPool( pool, BELOW_MIN_SIZE ) )
            {
                holder.release( release );
            }
            release.await();
            clock.forward( 110, TimeUnit.MILLISECONDS );
        }

        // then
        // currentPeakSize should be at MIN_SIZE / 5
        assertEquals( BELOW_MIN_SIZE, stateMonitor.currentPeakSize.get() );
        // target size should remain at MIN_SIZE
        assertEquals( MIN_SIZE, stateMonitor.targetSize.get() );
        // only the excess from the MAX_SIZE down to min size must have been disposed
        // +1 for the alarm from buildAPeakOfAcquiredResourcesAndTriggerAlarmWithSideEffects
        assertEquals( MAX_SIZE - MIN_SIZE + 1, stateMonitor.disposed.get() );

        stateMonitor.created.set( 0 );
        stateMonitor.disposed.set( 0 );

        // when
        // After the lull, recreate a peak
        buildAPeakOfAcquiredResourcesAndTriggerAlarmWithSideEffects( MAX_SIZE, clock, pool, holders );

        // then
        assertEquals( MAX_SIZE - MIN_SIZE + 1, stateMonitor.created.get() );
        assertEquals( 0, stateMonitor.disposed.get() );

    }

}