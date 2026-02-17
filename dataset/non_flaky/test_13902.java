class DummyClass_13902 {
    @Test
    public void testUpdatesAreWrittenToLogBeforeBeingAppliedToStore() throws Exception
    {
        int master = getCurrentMaster();
        addNode( master );
        int toKill = (master + 1) % dbs.length;
        HighlyAvailableGraphDatabase dbToKill = dbs[toKill];

        final CountDownLatch latch1 = new CountDownLatch( 1 );

        final HighlyAvailableGraphDatabase masterDb = dbs[master];
        masterDb.getDependencyResolver().resolveDependency( ClusterClient.class ).addClusterListener(
                new ClusterListener.Adapter()
                {
                    @Override
                    public void leftCluster( InstanceId instanceId, URI member )
                    {
                        latch1.countDown();
                        masterDb.getDependencyResolver().resolveDependency( ClusterClient.class )
                                .removeClusterListener( this );
                    }

}