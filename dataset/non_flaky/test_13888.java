class DummyClass_13888 {
    @Test
    public void canGetHaBean() throws Throwable
    {
        startCluster( 1 );
        HighAvailability ha = ha( cluster.getMaster() );
        assertNotNull( "could not get ha bean", ha );
        assertMasterInformation( ha );
    }

}