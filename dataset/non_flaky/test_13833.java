class DummyClass_13833 {
    @Test
    public void testUpdatedTargetSizeOnlyOnChange() throws Exception
    {
        StringLogger logger = mock( StringLogger.class );
        LoggingResourcePoolMonitor monitor = new LoggingResourcePoolMonitor( logger );

        monitor.updatedTargetSize( 10 );
        verify( logger, times( 1 ) ).debug( anyString() );

        monitor.updatedTargetSize( 10 );
        verify( logger, times( 1 ) ).debug( anyString() );

        monitor.updatedTargetSize( 11 );
        verify( logger, times( 2 ) ).debug( anyString() );
    }

}