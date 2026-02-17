class DummyClass_112129 {
    @Test
    public void assertFindLatestJobRegisterStatisticsWhenTableIsEmpty() {
        assertFalse(repository.findLatestJobRegisterStatistics().isPresent());
    }

}