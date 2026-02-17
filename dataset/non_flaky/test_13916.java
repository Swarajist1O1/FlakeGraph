class DummyClass_13916 {
    @Test
    public void shouldUpdateSlaveCacheWhenRemovingRelationshipGroupFromDenseNode() throws Throwable
    {
        ClusterManager manager = new ClusterManager( clusterOfSize( 3 ), root.directory(),
                                                     stringMap( tx_push_factor.name(), "2",
                                                                cache_type.name(), "strong",
                                                                dense_node_threshold.name(), "" + DENSE_NODE ) );
        try
        {
            // given
            manager.start();
            ClusterManager.ManagedCluster cluster = manager.getDefaultCluster();
            cluster.await( ClusterManager.masterAvailable() );
            cluster.await( ClusterManager.masterSeesAllSlavesAsAvailable() );
            HighlyAvailableGraphDatabase master = cluster.getMaster();
            long nodeId; // a dense node
            try ( Transaction tx = master.beginTx() )
            {
                Node node = master.createNode();
                for ( int i = 0; i < DENSE_NODE; i++ )
                {
                    node.createRelationshipTo( master.createNode(), withName( "FOO" ) );
                }
                master.createNode().createRelationshipTo( node, withName( "BAR" ) );

                tx.success();
                nodeId = node.getId();
            }
            // fully cache node on all instances
            int count = 0;
            for ( HighlyAvailableGraphDatabase db : cluster.getAllMembers() )
            {
                try ( Transaction tx = db.beginTx() )
                {
                    int these = count( db.getNodeById( nodeId ).getRelationships() );
                    assertTrue( String.format( "expected=%s, count here=%s", count, these ),
                                these != 0 && (count == 0 || these == count) );
                    count = these;
                    tx.success();
                }
            }

            // when
            try ( Transaction tx = master.beginTx() )
            {
                for ( Relationship relationship : master.getNodeById( nodeId ).getRelationships( withName( "BAR" ) ) )
                {
                    relationship.delete();
                }
                tx.success();
            }

            // then
            HighlyAvailableGraphDatabase slave = cluster.getAnySlave();
            try ( Transaction tx = slave.beginTx() )
            {
                List<String> relationships = new ArrayList<>();
                for ( Relationship relationship : slave.getNodeById( nodeId ).getRelationships() )
                {
                    relationships.add( String.format( "(%d)-[%d:%s]->(%d)",
                                                      relationship.getStartNode().getId(),
                                                      relationship.getId(), relationship.getType().name(),
                                                      relationship.getEndNode().getId() ) );
                }
                assertEquals( joinLines( relationships ), count - 1, relationships.size() );
                assertEquals( count - 1, count( slave.getNodeById( nodeId ).getRelationships() ) );

                tx.success();
            }
        }
        finally
        {
            manager.shutdown();
        }
    }

}