class DummyClass_13890 {
    @Test
    public void testUpdatePullWorksAndUpdatesLastUpdateTime() throws Throwable
    {
        startCluster( 2 );
        HighlyAvailableGraphDatabase master = cluster.getMaster();
        HighlyAvailableGraphDatabase slave = cluster.getAnySlave();
        Transaction tx = master.beginTx();
        master.createNode();
        tx.success();
        tx.finish();
        HighAvailability slaveBean = ha( slave );
        DateFormat format = new SimpleDateFormat( "yyyy-MM-DD kk:mm:ss.SSSZZZZ" );
        // To begin with, no updates
        slaveBean.update();
        long timeUpdated = format.parse( slaveBean.getLastUpdateTime() ).getTime();
        assertTrue( timeUpdated > 0 );
    }

}