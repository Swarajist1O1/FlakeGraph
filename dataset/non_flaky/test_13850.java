class DummyClass_13850 {
    @Test
    public void shouldMaintainPoolAtHighWatermarkWhenConcurrentUsagePassesMinSize() throws Exception
    {
        // given
        final int MIN_SIZE = 50;
        final int MAX_SIZE = 200;
        final int MID_SIZE = 90;

        StatefulMonitor stateMonitor = new StatefulMonitor();
        FakeClock clock = new FakeClock();
        final ResourcePool<Something> pool = getResourcePool( stateMonitor, clock, MIN_SIZE );
        List<ResourceHolder> holders = new LinkedList<ResourceHolder>();

        buildAPeakOfAcquiredResourcesAndTriggerAlarmWithSideEffects( MAX_SIZE, clock, pool, holders );

        // when
        // After the peak, stay at MID_SIZE concurrent usage, using up all already present resources in the process
        // but also keeping the high watermark above the MIN_SIZE
        clock.forward( 110, TimeUnit.MILLISECONDS );
        // Requires some rounds to happen, since there is constant racing between releasing and acquiring which does
        // not always result in reaping of resources, as there is reuse
        for ( int i = 0; i < 10; i++ )
        {
            // The latch is necessary to reduce races between batches
            CountDownLatch release = new CountDownLatch( MID_SIZE );
            for ( ResourceHolder holder : acquireFromPool( pool, MID_SIZE ) )
            {
                holder.release( release );
            }
            release.await();
            clock.forward( 110, TimeUnit.MILLISECONDS );
        }

        // then
        // currentPeakSize should be at MID_SIZE
        assertEquals( MID_SIZE, stateMonitor.currentPeakSize.get() );
        // target size too
        assertEquals( MID_SIZE, stateMonitor.targetSize.get() );
        // only the excess from the MAX_SIZE down to mid size must have been disposed
        // +1 for the alarm from buildAPeakOfAcquiredResourcesAndTriggerAlarmWithSideEffects
        assertEquals( MAX_SIZE - MID_SIZE + 1, stateMonitor.disposed.get() );
    }

}