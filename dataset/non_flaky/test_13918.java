class DummyClass_13918 {
    @Test
    public void testMasterStopsWritesWhenMajorityIsUnavailable() throws Throwable
    {
        File root = TargetDirectory.forTest( getClass() ).cleanDirectory(
                "testMasterStopsWritesWhenMajorityIsUnavailable" );
        ClusterManager clusterManager = new ClusterManager( clusterOfSize( 3 ), root,
                MapUtil.stringMap( HaSettings.tx_push_factor.name(), "2", HaSettings.state_switch_timeout.name(), "5s"
                ) );
        try
        {
            clusterManager.start();
            ClusterManager.ManagedCluster cluster = clusterManager.getDefaultCluster();
            cluster.await( ClusterManager.masterAvailable(  ) );
            cluster.await( ClusterManager.masterSeesAllSlavesAsAvailable() );

            HighlyAvailableGraphDatabase master = cluster.getMaster();

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
            ClusterManager.RepairKit rk2 = cluster.fail( slave2 );

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
//            waitOnRoleIsAvailable( master, latch4, HighAvailabilityModeSwitcher.MASTER );
            waitOnRoleIsAvailable( master, latch5, HighAvailabilityModeSwitcher.SLAVE );

            rk2.repair();

            latch3.await();

            cluster.await( ClusterManager.masterAvailable( slave1, slave2 ) );

//            latch4.await();
            latch5.await();

            cluster.await( ClusterManager.masterAvailable(  ) );

            assertTrue( master.isMaster() );
            assertFalse( slave2.isMaster() );

            Node finalNode = doTx( master );

            try ( Transaction transaction = slave2.beginTx() )
            {
                slave2.getNodeById( finalNode.getId() );
                transaction.success();
            }
        }
        finally
        {
            clusterManager.stop();
        }
    }

}