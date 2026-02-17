class DummyClass_112119 {
    @Test
    public void assertFindTaskRunningStatisticsWhenTableIsEmpty() {
        assertThat(repository.findTaskRunningStatistics(new Date()).size(), is(0));
    }

}