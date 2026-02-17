class DummyClass_13901 {
    @Test
    public void testTransactionsPulled() throws Exception
    {
        final HighlyAvailableGraphDatabase master =
                (HighlyAvailableGraphDatabase) new TestHighlyAvailableGraphDatabaseFactory().
                newHighlyAvailableDatabaseBuilder( TargetDirectory.forTest( TestClientThreadIsolation.class ).cleanDirectory(
                        "master" ).getAbsolutePath() ).
                setConfig( ClusterSettings.server_id, "1" ).
                newGraphDatabase();

        final HighlyAvailableGraphDatabase slave1 =
                (HighlyAvailableGraphDatabase) new TestHighlyAvailableGraphDatabaseFactory().
                newHighlyAvailableDatabaseBuilder( TargetDirectory.forTest( TestClientThreadIsolation.class ).cleanDirectory(
                        "slave1" ).getAbsolutePath() ).
                setConfig( ClusterSettings.cluster_server, "127.0.0.1:5002" ).
                setConfig( ClusterSettings.initial_hosts, "127.0.0.1:5001" ).
                setConfig( ClusterSettings.server_id, "2" ).
                setConfig( HaSettings.max_concurrent_channels_per_slave, "2" ).
                setConfig( HaSettings.ha_server, "127.0.0.1:8001" ).
                newGraphDatabase();

        Transaction masterTx = master.beginTx();
        master.createNode().createRelationshipTo( master.createNode(),
                DynamicRelationshipType.withName( "master" ) ).setProperty(
                "largeArray", new int[20000] );
        masterTx.success();
        masterTx.finish();

        Thread thread1 = new Thread( new Runnable()
        {
            public void run()
            {
                // TODO Figure out how to do this
//                Master masterClient = slave1.getBroker().getMaster().first();
//                Response<Integer> response = masterClient.createRelationshipType(
//                        slave1.getSlaveContext( 10 ), "name" );
//                slave1.receive( response ); // will be suspended here
//                response.close();
            }

}