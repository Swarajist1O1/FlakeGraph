class DummyClass_112123 {
    @Test
    public void assertFindJobRunningStatisticsWhenTableIsEmpty() {
        assertThat(repository.findJobRunningStatistics(new Date()).size(), is(0));
    }

}