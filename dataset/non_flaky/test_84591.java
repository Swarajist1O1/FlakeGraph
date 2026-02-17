class DummyClass_84591 {
    @Test
    public void testBasicUsageOfApisAndRecipesInCluster() throws Exception {
        try (TestingCluster cluster = new TestingCluster(3)) {
            cluster.start();
            doTest(cluster.getConnectString());
        }
    }

}