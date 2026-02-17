class DummyClass_13921 {
    @Test
    public void emptyForeignDbShouldJoinAfterHavingItsEmptyDbDeleted() throws Exception
    {
        // GIVEN
        // -- one instance running
        firstInstance = new TestHighlyAvailableGraphDatabaseFactory()
                .newHighlyAvailableDatabaseBuilder( DIR.cleanDirectory( "1" ).getAbsolutePath() )
                .setConfig( server_id, "1" )
                .setConfig( cluster_server, "127.0.0.1:5001" )
                .setConfig( ha_server, "127.0.0.1:6031" )
                .setConfig( initial_hosts, "127.0.0.1:5001" )
                .newGraphDatabase();
        // -- another instance preparing to join with a store with a different store ID
        String foreignDbStoreDir = createAnotherStore( DIR.cleanDirectory( "2" ), 0 );

        // WHEN
        // -- the other joins
        foreignInstance = new TestHighlyAvailableGraphDatabaseFactory()
                .newHighlyAvailableDatabaseBuilder( foreignDbStoreDir )
                .setConfig( server_id, "2" )
                .setConfig( initial_hosts, "127.0.0.1:5001" )
                .setConfig( cluster_server, "127.0.0.1:5002" )
                .setConfig( ha_server, "127.0.0.1:6032" )
                .newGraphDatabase();
        // -- and creates a node
        long foreignNode = createNode( foreignInstance, "foreigner" );

        // THEN
        // -- that node should arrive at the master
        assertEquals( foreignNode, findNode( firstInstance, "foreigner" ) );
    }

}