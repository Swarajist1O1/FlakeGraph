class DummyClass_88795 {
    @Test
    public void testCallProcessIsRunning() {
        UUID procId = UUID.randomUUID();

        Future<?> fut = mock(Future.class);
        doReturn(false).when(fut).isDone();
        metadataStorage.put(procId, fut);

        LongRunningProcessPingTask pingTask = createTask(procId);

        List<LongRunningProcessStatus> statuses = pingTask.call();

        assertEquals(1, statuses.size());

        LongRunningProcessStatus status = statuses.get(0);
        assertEquals(LongRunningProcessState.RUNNING, status.getState());
        assertNull(status.getException());

        assertEquals(1, metadataStorage.size());
    }

}