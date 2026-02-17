class DummyClass_112111 {
    @Test
    public void assertAddJobRunningStatistics() {
        assertTrue(repository.add(new TaskRunningStatistics(100, new Date())));
    }

}