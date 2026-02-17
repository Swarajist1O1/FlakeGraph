class DummyClass_112084 {
    @Test(expected = JobConfigurationException.class)
    public void assertGetStrategyFailureWhenNotStrategyClass() {
        JobShardingStrategyFactory.getStrategy(Object.class.getName());
    }

}