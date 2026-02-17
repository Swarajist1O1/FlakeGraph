class DummyClass_112083 {
    @Test(expected = JobConfigurationException.class)
    public void assertGetStrategyFailureWhenClassNotFound() {
        JobShardingStrategyFactory.getStrategy("NotClass");
    }

}