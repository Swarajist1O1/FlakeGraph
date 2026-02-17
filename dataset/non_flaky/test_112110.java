class DummyClass_112110 {
    @Test
    public void assertAddTaskRunningStatistics() {
        assertTrue(repository.add(new TaskRunningStatistics(100, new Date())));
    }

}