class DummyClass_13930 {
    @Test
    public void startTxAsSlaveAndFinishItAfterHavingSwitchedToMasterShouldNotSucceed() throws Exception
    {
        // GIVEN
        GraphDatabaseService db = cluster.getAnySlave();
        takeTheLeadInAnEventualMasterSwitch( db );

        // WHEN
        Transaction tx = db.beginTx();
        try
        {
            db.createNode().setProperty( "name", "slave" );
            tx.success();
        }
        finally
        {
            cluster.shutdown( cluster.getMaster() );
            assertFinishGetsTransactionFailure( tx );
        }

        cluster.await( masterAvailable() );

        // THEN
        assertEquals( db, cluster.getMaster() );
        // to prevent a deadlock scenario which occurs if this test exists (and @After starts)
        // before the db has recovered from its KERNEL_PANIC
        awaitFullyOperational( db );
    }

}