class DummyClass_13919 {
    @Test
    public void testInstanceCanBeReplacedToReestablishQuorum() throws Throwable
    {
        File root = TargetDirectory.forTest( getClass() ).cleanDirectory(
                "testInstanceCanBeReplacedToReestablishQuorum"
        );
        ClusterManager clusterManager = new ClusterManager( clusterOfSize( 3 ), root,
                MapUtil.stringMap( HaSettings.tx_push_factor.name(), "2", HaSettings.state_switch_timeout.name(), "5s" ) );
        clusterManager.start();
        ClusterManager.ManagedCluster cluster = clusterManager.getDefaultCluster();

        HighlyAvailableGraphDatabase master = cluster.getMaster();

        cluster.await( ClusterManager.masterSeesAllSlavesAsAvailable() );

        doTx( master );

        final CountDownLatch latch1 = new CountDownLatch( 1 );
        waitOnHeartbeatFail( master, latch1 );

        HighlyAvailableGraphDatabase slave1 = cluster.getAnySlave();
        cluster.fail( slave1 );

        latch1.await();
        slave1.shutdown();

        doTx( master );

        final CountDownLatch latch2 = new CountDownLatch( 1 );
        waitOnHeartbeatFail( master, latch2 );

        HighlyAvailableGraphDatabase slave2 = cluster.getAnySlave( slave1 );
        cluster.fail( slave2 );

        latch2.await();

        // The master should stop saying that it's master
        assertFalse( master.isMaster() );

        try
        {
            doTx( master );
            fail( "After both slaves fail txs should not go through" );
        }
        catch ( TransactionFailureException e )
        {
            assertEquals( "Timeout waiting for cluster to elect master", e.getMessage() );
        }

        // This is not a hack, this simulates a period of inactivity in the cluster.
        Thread.sleep( 120000 ); // TODO Define "inactivity" and await that condition instead of 120 seconds.

        final CountDownLatch latch3 = new CountDownLatch( 1 );
        final CountDownLatch latch4 = new CountDownLatch( 1 );
        final CountDownLatch latch5 = new CountDownLatch( 1 );
        waitOnHeartbeatAlive( master, latch3 );
        waitOnRoleIsAvailable( master, latch4, HighAvailabilityModeSwitcher.MASTER );
        waitOnRoleIsAvailable( master, latch5, HighAvailabilityModeSwitcher.SLAVE );

        HighlyAvailableGraphDatabase replacement =
                (HighlyAvailableGraphDatabase) new TestHighlyAvailableGraphDatabaseFactory().
                newHighlyAvailableDatabaseBuilder( new File( root, "replacement" ).getAbsolutePath() ).
                setConfig( ClusterSettings.cluster_server, ":5010" ).
                setConfig( HaSettings.ha_server, ":6010" ).
                setConfig( ClusterSettings.server_id, "3" ).
                setConfig( ClusterSettings.initial_hosts, cluster.getInitialHostsConfigString() ).
                setConfig( HaSettings.tx_push_factor, "0" ).
                newGraphDatabase();

        latch3.await();
        latch4.await();
        latch5.await();

        assertTrue( master.isMaster() );
        assertFalse( replacement.isMaster() );

        Node finalNode = doTx( master );

        Transaction transaction = replacement.beginTx();
        try
        {
            replacement.getNodeById( finalNode.getId() );
        }
        finally
        {
            transaction.finish();
        }

        clusterManager.stop();
        replacement.shutdown();
    }

}