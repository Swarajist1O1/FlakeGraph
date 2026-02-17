class DummyClass_91475 {
@TestLogging("_root:DEBUG,org.elasticsearch.action.admin.cluster.state:TRACE")
    public void testSimpleOnlyMasterNodeElection() throws IOException {
        logger.info("--> start data node / non master node");
        internalCluster().startNode(Settings.builder().put(Node.NODE_DATA_SETTING.getKey(), true).put(Node.NODE_MASTER_SETTING.getKey(), false)
            .put("discovery.initial_state_timeout", "1s"));
        try {
            assertThat(client().admin().cluster().prepareState().setMasterNodeTimeout("100ms").execute().actionGet().getState().nodes().getMasterNodeId(), nullValue());
            fail("should not be able to find master");
        } catch (MasterNotDiscoveredException e) {
            // all is well, no master elected
        }
        logger.info("--> start master node");
        final String masterNodeName = internalCluster().startNode(Settings.builder().put(Node.NODE_DATA_SETTING.getKey(), false).put(Node.NODE_MASTER_SETTING.getKey(), true));
        assertThat(internalCluster().nonMasterClient().admin().cluster().prepareState().execute().actionGet().getState().nodes().getMasterNode().getName(), equalTo(masterNodeName));
        assertThat(internalCluster().masterClient().admin().cluster().prepareState().execute().actionGet().getState().nodes().getMasterNode().getName(), equalTo(masterNodeName));

        logger.info("--> stop master node");
        internalCluster().stopCurrentMasterNode();

        try {
            assertThat(client().admin().cluster().prepareState().setMasterNodeTimeout("100ms").execute().actionGet().getState().nodes().getMasterNodeId(), nullValue());
            fail("should not be able to find master");
        } catch (MasterNotDiscoveredException e) {
            // all is well, no master elected
        }

        logger.info("--> start master node");
        final String nextMasterEligibleNodeName = internalCluster().startNode(Settings.builder().put(Node.NODE_DATA_SETTING.getKey(), false).put(Node.NODE_MASTER_SETTING.getKey(), true));
        assertThat(internalCluster().nonMasterClient().admin().cluster().prepareState().execute().actionGet().getState().nodes().getMasterNode().getName(), equalTo(nextMasterEligibleNodeName));
        assertThat(internalCluster().masterClient().admin().cluster().prepareState().execute().actionGet().getState().nodes().getMasterNode().getName(), equalTo(nextMasterEligibleNodeName));
    }

}