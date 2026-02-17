class DummyClass_13923 {
    @Test
    public void makeSureUpdatePullerGetsGoingAfterMasterSwitch() throws Throwable
    {
        File root = TargetDirectory.forTest( getClass() ).cleanDirectory( testName.getMethodName() );
        ClusterManager clusterManager = new ClusterManager( clusterOfSize( 3 ), root, MapUtil.stringMap(
                HaSettings.pull_interval.name(), PULL_INTERVAL+"ms",
                ClusterSettings.heartbeat_interval.name(), "2s",
                ClusterSettings.heartbeat_timeout.name(), "30s") );
        clusterManager.start();
        cluster = clusterManager.getDefaultCluster();
        cluster.await( allSeesAllAsAvailable() );

        cluster.info( "### Creating initial dataset" );
        long commonNodeId = createNodeOnMaster();

        HighlyAvailableGraphDatabase master = cluster.getMaster();
        setProperty( master, commonNodeId, 1 );
        cluster.info( "### Initial dataset created" );
        awaitPropagation( 1, commonNodeId, cluster );

        cluster.info( "### Shutting down master" );
        ClusterManager.RepairKit masterShutdownRK = cluster.shutdown( master );

        cluster.info( "### Awaiting new master" );
        cluster.await( masterAvailable( master ) );
        cluster.await( masterSeesSlavesAsAvailable( 1 ) );

        cluster.info( "### Doing a write to master" );
        setProperty( cluster.getMaster(), commonNodeId, 2 );
        awaitPropagation( 2, commonNodeId, cluster, master );

        cluster.info( "### Repairing cluster" );
        masterShutdownRK.repair();
        cluster.await( masterAvailable() );
        cluster.await( masterSeesSlavesAsAvailable( 2 ) );
        cluster.await( allSeesAllAsAvailable() );

        cluster.info( "### Awaiting change propagation" );
        awaitPropagation( 2, commonNodeId, cluster );
    }

}