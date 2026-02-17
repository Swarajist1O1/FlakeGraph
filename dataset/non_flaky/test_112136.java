class DummyClass_112136 {
    @Test(expected = IllegalArgumentException.class)
    public void assertBuildWhenTotalSHardingCountIsNegative() {
        JobCoreConfiguration.newBuilder(null, "0/1 * * * * ?", -1).build();
    }

}