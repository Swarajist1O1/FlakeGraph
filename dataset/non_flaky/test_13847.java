class DummyClass_13847 {
    @Test
    public void shouldUpdateTargetSizeWhenSpikesOccur() throws Exception
    {
        // given
        final int MIN_SIZE = 5;
        final int MAX_SIZE = 10;

        StatefulMonitor stateMonitor = new StatefulMonitor();
        FakeClock clock = new FakeClock();
        final ResourcePool<Something> pool = getResourcePool( stateMonitor, clock, MIN_SIZE );

        // when
        List<ResourceHolder> holders = acquireFromPool( pool, MAX_SIZE );
        clock.forward( 110, TimeUnit.MILLISECONDS );
        holders.addAll( acquireFromPool( pool, 1 ) ); // Needed to trigger the alarm

        // then
        assertEquals( MAX_SIZE + 1, stateMonitor.currentPeakSize.get() );
        // We have not released anything, so targetSize will not be reduced
        assertEquals( MAX_SIZE + 1, stateMonitor.targetSize.get() ); // + 1 from the acquire

        for ( ResourceHolder holder : holders )
        {
            holder.end();
        }
    }

}