class DummyClass_13920 {
    @Test
    public void creatingConstraintOnSlaveIsNotAllowed() throws Exception
    {
        // given
        ClusterManager.ManagedCluster cluster = clusterRule.startCluster();
        HighlyAvailableGraphDatabase slave = cluster.getAnySlave();

        slave.beginTx();
        try
        {
            ConstraintCreator constraintCreator = slave.schema()
                    .constraintFor( DynamicLabel.label( "LabelName" ) ).assertPropertyIsUnique( "PropertyName" );

            // when
            constraintCreator.create();
            fail( "should have thrown exception" );
        }
        catch ( InvalidTransactionTypeException e )
        {
            assertThat(e.getMessage(), equalTo("Modifying the database schema can only be done on the master server, " +
                    "this server is a slave. Please issue schema modification commands directly to the master."));
        }
    }

}