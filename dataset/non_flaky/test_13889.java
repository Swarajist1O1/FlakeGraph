class DummyClass_13889 {
    @Test
    public void testLatestTxInfoIsCorrect() throws Throwable
    {
        startCluster( 1 );
        HighlyAvailableGraphDatabase db = cluster.getMaster();
        HighAvailability masterHa = ha( db );
        long lastCommitted = masterHa.getLastCommittedTxId();
        try ( Transaction tx = db.beginTx() )
        {
            db.createNode();
            tx.success();
        }
        assertEquals( lastCommitted + 1, masterHa.getLastCommittedTxId() );
    }

}