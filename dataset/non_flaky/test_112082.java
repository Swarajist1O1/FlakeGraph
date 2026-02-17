class DummyClass_112082 {
    @Test
    public void assertGetDefaultStrategy() {
        assertThat(JobShardingStrategyFactory.getStrategy(null), instanceOf(AverageAllocationJobShardingStrategy.class));
    }

}