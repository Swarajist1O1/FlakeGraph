class DummyClass_13929 {
    @Test
    public void givenClusterWhenShutdownMasterThenCannotStartTransactionOnSlave() throws Throwable
    {
        // Given
        ClusterManager clusterManager = new ClusterManager(
                fromXml( getClass().getResource( "/threeinstances.xml" ).toURI() ),
                forTest( getClass() ).cleanDirectory( "testCluster" ),
                stringMap( HaSettings.ha_server.name(), ":6001-6005", HaSettings.tx_push_factor.name(), "2" ) );
        try
        {
            clusterManager.start();

            clusterManager.getDefaultCluster().await( ClusterManager.allSeesAllAsAvailable() );

            GraphDatabaseAPI master = clusterManager.getDefaultCluster().getMaster();
            final GraphDatabaseAPI slave = clusterManager.getDefaultCluster().getAnySlave();

            // When
            final FutureTask<Boolean> result = new FutureTask<>( new Callable<Boolean>()
            {
                @Override
                public Boolean call() throws Exception
                {
                    try ( Transaction tx = slave.beginTx() )
                    {
                        tx.acquireWriteLock( slave.getNodeById( 0 ) );
                        // Fail
                        return false;
                    }
                    catch ( Exception e )
                    {
                        // Ok!
                        return true;
                    }
                }

}