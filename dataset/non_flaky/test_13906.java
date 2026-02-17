class DummyClass_13906 {
    @Test
    public void givenClusterWithCreatedIndexWhenDeleteIndexOnMasterThenIndexIsDeletedOnSlave() throws Throwable
    {
        ClusterManager clusterManager =
            new ClusterManager( fromXml( getClass().getResource( "/threeinstances.xml" ).toURI() ),
                TargetDirectory.forTest( getClass() ).cleanDirectory( "testCluster" ),
                MapUtil.stringMap( HaSettings.ha_server.name(), ":6001-6005",
                        HaSettings.tx_push_factor.name(), "2" ));
        try
        {
            // Given
            clusterManager.start();

            clusterManager.getDefaultCluster().await( ClusterManager.allSeesAllAsAvailable() );

            GraphDatabaseAPI master = clusterManager.getDefaultCluster().getMaster();
            try ( Transaction tx = master.beginTx() )
            {
                master.index().forNodes( "Test" );
                tx.success();
            }

            HighlyAvailableGraphDatabase aSlave = clusterManager.getDefaultCluster().getAnySlave();
            try ( Transaction tx = aSlave.beginTx() )
            {
                assertThat( aSlave.index().existsForNodes( "Test" ), equalTo( true ) );
                tx.success();
            }

            // When
            try ( Transaction tx = master.beginTx() )
            {
                master.index().forNodes( "Test" ).delete();
                tx.success();
            }

            // Then
            HighlyAvailableGraphDatabase anotherSlave = clusterManager.getDefaultCluster().getAnySlave();
            try ( Transaction tx = anotherSlave.beginTx() )
            {
                assertThat( anotherSlave.index().existsForNodes( "Test" ), equalTo( false ) );
                tx.success();
            }
        }
        finally
        {
            clusterManager.stop();
        }
    }

}