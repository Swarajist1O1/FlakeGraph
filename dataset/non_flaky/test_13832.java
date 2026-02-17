class DummyClass_13832 {
    @Test
    public void testUpdatedCurrentPeakSizeLogsOnlyOnChange() throws Exception
    {
        StringLogger logger = mock( StringLogger.class );
        LoggingResourcePoolMonitor monitor = new LoggingResourcePoolMonitor( logger );

        monitor.updatedCurrentPeakSize( 10 );
        verify( logger, times( 1 ) ).debug( anyString() );

        monitor.updatedCurrentPeakSize( 10 );
        verify( logger, times( 1 ) ).debug( anyString() );

        monitor.updatedCurrentPeakSize( 11 );
        verify( logger, times( 2 ) ).debug( anyString() );
    }

}