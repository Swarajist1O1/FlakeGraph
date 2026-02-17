class DummyClass_77458 {
    @TestGroup(enabled = false, sysProperty = OpenSearchIntegTestCase.SYSPROP_THIRDPARTY)
    public ClusterService clusterService() {
        return internalCluster().clusterService();
    }

}