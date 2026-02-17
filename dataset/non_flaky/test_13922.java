class DummyClass_13922 {
    @Test
    public void nonEmptyForeignDbShouldNotBeAbleToJoin() throws Exception
    {
        // GIVEN
        // -- one instance running
        firstInstance = new TestHighlyAvailableGraphDatabaseFactory()
                .newHighlyAvailableDatabaseBuilder( DIR.cleanDirectory( "1" ).getAbsolutePath() )
                .setConfig( server_id, "1" )
                .setConfig( initial_hosts, "127.0.0.1:5001" )
                .setConfig( cluster_server, "127.0.0.1:5001" )
                .setConfig( ha_server, "127.0.0.1:6041" )
                .newGraphDatabase();
        createNodes( firstInstance, 3, "first" );
        // -- another instance preparing to join with a store with a different store ID
        String foreignDbStoreDir = createAnotherStore( DIR.cleanDirectory( "2" ), 1 );

        // WHEN
        // -- the other joins
        foreignInstance = new TestHighlyAvailableGraphDatabaseFactory()
                .newHighlyAvailableDatabaseBuilder( foreignDbStoreDir )
                .setConfig( server_id, "2" )
                .setConfig( initial_hosts, "127.0.0.1:5001" )
                .setConfig( cluster_server, "127.0.0.1:5002" )
                .setConfig( ha_server, "127.0.0.1:6042" )
                .setConfig( state_switch_timeout, "5s" )
                .newGraphDatabase();

        try
        {
            // THEN
            // -- that node should arrive at the master
            createNode( foreignInstance, "foreigner" );
            fail( "Shouldn't be able to create a node, since it shouldn't have joined" );
        }
        catch ( Exception e )
        {
            // Good
        }
    }

}