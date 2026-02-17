class DummyClass_13925 {
    @Test
    public void shouldPullUpdatesOnStartupNoMatterWhat() throws Exception
    {
        GraphDatabaseService slave = null;
        GraphDatabaseService master = null;
        try
        {
            File testRootDir = TargetDirectory.forTest( getClass() ).cleanDirectory( testName.getMethodName() );
            File masterDir = new File( testRootDir, "master" );
            master = new TestHighlyAvailableGraphDatabaseFactory().
                    newHighlyAvailableDatabaseBuilder( masterDir.getAbsolutePath() )
                    .setConfig( ClusterSettings.server_id, "1" )
                    .setConfig( ClusterSettings.initial_hosts, ":5001" )
                    .newGraphDatabase();

            // Copy the store, then shutdown, so update pulling later makes sense
            File slaveDir = new File( testRootDir, "slave" );
            slave = new TestHighlyAvailableGraphDatabaseFactory().
                    newHighlyAvailableDatabaseBuilder( slaveDir.getAbsolutePath() )
                    .setConfig( ClusterSettings.server_id, "2" )
                    .setConfig( ClusterSettings.initial_hosts, ":5001" )
                    .newGraphDatabase();

            // Required to block until the slave has left for sure
            final CountDownLatch slaveLeftLatch = new CountDownLatch( 1 );

            final ClusterClient masterClusterClient = ( (HighlyAvailableGraphDatabase) master ).getDependencyResolver()
                    .resolveDependency( ClusterClient.class );

            masterClusterClient.addClusterListener( new ClusterListener.Adapter()
            {
                @Override
                public void leftCluster( InstanceId instanceId, URI member )
                {
                    slaveLeftLatch.countDown();
                    masterClusterClient.removeClusterListener( this );
                }

}