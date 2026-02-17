class DummyClass_13848 {
    @Test
    public void shouldKeepSmallPeakAndNeverDisposeIfAcquireAndReleaseContinuously() throws Exception
    {
        // given
        final int MIN_SIZE = 1;

        StatefulMonitor stateMonitor = new StatefulMonitor();
        FakeClock clock = new FakeClock();
        final ResourcePool<Something> pool = getResourcePool( stateMonitor, clock, MIN_SIZE );

        // when
        for ( int i = 0; i < 200; i++ )
        {
            List<ResourceHolder> newOnes = acquireFromPool( pool, 1 );
            CountDownLatch release = new CountDownLatch( newOnes.size() );
            for ( ResourceHolder newOne : newOnes )
            {
                newOne.release( release );
            }
            release.await();
        }

        // then
        assertEquals( -1, stateMonitor.currentPeakSize.get() ); // no alarm has rung, -1 is the default
        assertEquals( 1, stateMonitor.created.get() );
        assertEquals( 0, stateMonitor.disposed.get() ); // we should always be below min size, so 0 dispose calls
    }

}