class DummyClass_13907 {
    @Test
    public void testPullStorm() throws Throwable
    {
        // given

        ClusterManager clusterManager = new ClusterManager( ClusterManager.clusterWithAdditionalArbiters( 2, 1 ),
                testDirectory.directory(),
                stringMap( HaSettings.pull_interval.name(), "0",
                           HaSettings.tx_push_factor.name(), "1") );

        clusterManager.start();

        try
        {
            ClusterManager.ManagedCluster cluster = clusterManager.getDefaultCluster();
            cluster.await( ClusterManager.masterAvailable(  ) );
            cluster.await( ClusterManager.masterSeesSlavesAsAvailable( 1 ) );

            // Create data
            final HighlyAvailableGraphDatabase master = cluster.getMaster();
            {
                Transaction tx = master.beginTx();
                for ( int i = 0; i < 1000; i++ )
                {
                    master.createNode().setProperty( "foo", "bar" );
                }
                tx.success();
                tx.finish();
            }

            // Slave goes down
            HighlyAvailableGraphDatabase slave = cluster.getAnySlave();
            ClusterManager.RepairKit repairKit = cluster.fail( slave );

            // Create more data
            for ( int i = 0; i < 1000; i++ )
            {
                {
                    Transaction tx = master.beginTx();
                    for ( int j = 0; j < 1000; j++ )
                    {
                        master.createNode().setProperty( "foo", "bar" );
                        master.createNode().setProperty( "foo", "bar" );
                    }
                    tx.success();
                    tx.finish();
                }
            }

            // Slave comes back online
            repairKit.repair();

            cluster.await( ClusterManager.masterSeesSlavesAsAvailable( 1 ) );

            // when

            // Create 20 concurrent transactions
            System.out.println( "Pull storm" );
            ExecutorService executor = Executors.newFixedThreadPool( 20 );
            for ( int i = 0; i < 20; i++ )
            {
                executor.submit( new Runnable()
                {
                    @Override
                    public void run()
                    {
                        Transaction tx = master.beginTx();
                        master.createNode().setProperty( "foo", "bar" );
                        tx.success();
                        tx.finish(); // This should cause lots of concurrent calls to pullUpdate()
                    }

}