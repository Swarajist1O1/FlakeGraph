class DummyClass_13917 {
    @Test
    public void runTwoClusters() throws Throwable
    {
        File root = TargetDirectory.forTest( getClass() ).cleanDirectory( "cluster" );

        ClusterManager clusterManager = new ClusterManager(
                fromXml( getClass().getResource( "/twoclustertest.xml" ).toURI() ), root, MapUtil.stringMap() );

        try
        {
            clusterManager.start();
            ManagedCluster cluster1 = clusterManager.getCluster( "neo4j.ha" );

            long cluster1NodeId;
            {
                GraphDatabaseService master = cluster1.getMaster();
                logging.getLogger().info( "CREATE NODE" );
                Transaction tx = master.beginTx();
                Node node = master.createNode();
                node.setProperty( "cluster", "neo4j.ha" );
                cluster1NodeId = node.getId();
                logging.getLogger().info( "CREATED NODE" );
                tx.success();
                tx.finish();
            }

            ManagedCluster cluster2 = clusterManager.getCluster( "neo4j.ha2" );
            long cluster2NodeId;
            {
                GraphDatabaseService master = cluster2.getMaster();
                logging.getLogger().info( "CREATE NODE" );
                Transaction tx = master.beginTx();
                Node node = master.createNode();
                node.setProperty( "cluster", "neo4j.ha2" );
                cluster2NodeId = node.getId();
                logging.getLogger().info( "CREATED NODE" );
                tx.success();
                tx.finish();
            }

            // Verify properties in all cluster nodes
            for ( HighlyAvailableGraphDatabase highlyAvailableGraphDatabase : cluster1.getAllMembers() )
            {
                highlyAvailableGraphDatabase.getDependencyResolver().resolveDependency( UpdatePullerClient.class ).pullUpdates();

                Transaction transaction = highlyAvailableGraphDatabase.beginTx();
                assertEquals( "neo4j.ha", highlyAvailableGraphDatabase.getNodeById( cluster1NodeId ).getProperty(
                        "cluster" ) );
                transaction.finish();
            }

            for ( HighlyAvailableGraphDatabase highlyAvailableGraphDatabase : cluster2.getAllMembers() )
            {
                highlyAvailableGraphDatabase.getDependencyResolver().resolveDependency( UpdatePullerClient.class ).pullUpdates();

                Transaction transaction = highlyAvailableGraphDatabase.beginTx();
                assertEquals( "neo4j.ha2", highlyAvailableGraphDatabase.getNodeById( cluster2NodeId ).getProperty(
                        "cluster" ) );
                transaction.finish();
            }
        }
        finally
        {
            clusterManager.stop();
        }
    }

}