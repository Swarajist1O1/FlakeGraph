class DummyClass_13931 {
    @Test
    public void startTxAsSlaveAndFinishItAfterAnotherMasterBeingAvailableShouldNotSucceed() throws Exception
    {
        // GIVEN
        HighlyAvailableGraphDatabase db = cluster.getAnySlave();

        // WHEN
        HighlyAvailableGraphDatabase theOtherSlave;
        Transaction tx = db.beginTx();
        try
        {
            db.createNode().setProperty( "name", "slave" );
            tx.success();
        }
        finally
        {
            theOtherSlave = cluster.getAnySlave( db );
            takeTheLeadInAnEventualMasterSwitch( theOtherSlave );
            cluster.shutdown( cluster.getMaster() );
            assertFinishGetsTransactionFailure( tx );
        }

        cluster.await( ClusterManager.masterAvailable() );

        // THEN
        assertFalse( db.isMaster() );
        assertTrue( theOtherSlave.isMaster() );
        // to prevent a deadlock scenario which occurs if this test exists (and @After starts)
        // before the db has recovered from its KERNEL_PANIC
        awaitFullyOperational( db );
    }

}