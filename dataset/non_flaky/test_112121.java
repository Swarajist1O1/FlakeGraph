class DummyClass_112121 {
    @Test
    public void assertFindLatestTaskRunningStatisticsWhenTableIsEmpty() {
        assertFalse(repository.findLatestTaskRunningStatistics().isPresent());
    }

}