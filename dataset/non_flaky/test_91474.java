class DummyClass_91474 {
    @TestLogging("_root:debug,org.elasticsearch.action.admin.cluster.tasks:trace")
    public void testPendingUpdateTask() throws Exception {
        String node_0 = internalCluster().startNode();
        internalCluster().startCoordinatingOnlyNode(Settings.EMPTY);

        final ClusterService clusterService = internalCluster().getInstance(ClusterService.class, node_0);
        final CountDownLatch block1 = new CountDownLatch(1);
        final CountDownLatch invoked1 = new CountDownLatch(1);
        clusterService.submitStateUpdateTask("1", new ClusterStateUpdateTask() {
            @Override
            public ClusterState execute(ClusterState currentState) {
                invoked1.countDown();
                try {
                    block1.await();
                } catch (InterruptedException e) {
                    fail();
                }
                return currentState;
            }

}