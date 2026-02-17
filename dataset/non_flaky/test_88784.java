class DummyClass_88784 {
    @Test
    public void testCallProcessNotFound() {
        LongRunningProcessClearTask clearTask = createTask(UUID.randomUUID());

        List<LongRunningProcessStatus> statuses = clearTask.call();

        assertEquals(1, statuses.size());

        LongRunningProcessStatus status = statuses.get(0);
        assertEquals(LongRunningProcessState.NOT_FOUND, status.getState());
        assertNull(status.getException());

        assertEquals(0, metadataStorage.size());
    }

}