class DummyClass_43108 {
    @Test
    public void ensureDistributedQueryRunner()
    {
        assertThat(getQueryRunner().getNodeCount()).as("query runner node count")
                .isGreaterThanOrEqualTo(3);
    }

}