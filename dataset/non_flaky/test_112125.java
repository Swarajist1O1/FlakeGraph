class DummyClass_112125 {
    @Test
    public void assertFindLatestJobRunningStatisticsWhenTableIsEmpty() {
        assertFalse(repository.findLatestJobRunningStatistics().isPresent());
    }

}