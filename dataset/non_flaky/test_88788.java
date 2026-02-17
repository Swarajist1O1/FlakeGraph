class DummyClass_88788 {
    @Test
    public void testCallProcessNotFound() {
        LongRunningProcessStopTask stopTask = createTask(UUID.randomUUID(), true);

        List<LongRunningProcessStatus> statuses = stopTask.call();

        assertEquals(1, statuses.size());

        LongRunningProcessStatus status = statuses.get(0);
        assertEquals(LongRunningProcessState.NOT_FOUND, status.getState());
        assertNull(status.getException());

        assertEquals(0, metadataStorage.size());
    }

}