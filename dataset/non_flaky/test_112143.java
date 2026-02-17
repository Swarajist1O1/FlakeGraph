class DummyClass_112143 {
    @Test(expected = JobConfigurationException.class)
    public void assertNewWhenPairFormatInvalid() {
        new ShardingItemParameters("xxx-xxx");
    }

}