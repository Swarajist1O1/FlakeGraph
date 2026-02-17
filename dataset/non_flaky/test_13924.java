class DummyClass_13924 {
    @Test
    public void pullUpdatesShellAppPullsUpdates() throws Throwable
    {
        File root = TargetDirectory.forTest( getClass() ).cleanDirectory( testName.getMethodName() );
        Map<Integer, Map<String, String>> instanceConfig = new HashMap<>();
        for (int i = 1; i <= 2; i++)
        {
            Map<String, String> thisInstance =
                    MapUtil.stringMap( ShellSettings.remote_shell_port.name(), "" + (SHELL_PORT + i) );
            instanceConfig.put( i, thisInstance );
        }
        ClusterManager clusterManager = new ClusterManager( clusterOfSize( 2 ), root, MapUtil.stringMap(
                HaSettings.pull_interval.name(), "0",
                HaSettings.tx_push_factor.name(), "0" ,
                ShellSettings.remote_shell_enabled.name(), "true"
                ), instanceConfig );
        clusterManager.start();
        cluster = clusterManager.getDefaultCluster();

        long commonNodeId = createNodeOnMaster();

        setProperty( cluster.getMaster(), commonNodeId, 1 );
        callPullUpdatesViaShell( 2 );
        HighlyAvailableGraphDatabase slave = cluster.getAnySlave();
        try ( Transaction tx = slave.beginTx() )
        {
            assertEquals( 1, slave.getNodeById( commonNodeId ).getProperty( "i" ) );
        }
    }

}