class DummyClass_88794 {
    @Test
    public void testCallProcessNotFound() {
        LongRunningProcessPingTask pingTask = createTask(UUID.randomUUID());

        List<LongRunningProcessStatus> statuses = pingTask.call();

        assertEquals(1, statuses.size());

        LongRunningProcessStatus status = statuses.get(0);
        assertEquals(LongRunningProcessState.NOT_FOUND, status.getState());
        assertNull(status.getException());

        assertEquals(0, metadataStorage.size());
    }

}