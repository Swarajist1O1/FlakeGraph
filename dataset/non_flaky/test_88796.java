class DummyClass_88796 {
    @Test
    public void testCallProcessIsDone() {
        UUID procId = UUID.randomUUID();

        Future<?> fut = mock(Future.class);
        doReturn(true).when(fut).isDone();
        metadataStorage.put(procId, fut);

        LongRunningProcessPingTask pingTask = createTask(procId);

        List<LongRunningProcessStatus> statuses = pingTask.call();

        assertEquals(1, statuses.size());

        LongRunningProcessStatus status = statuses.get(0);
        assertEquals(LongRunningProcessState.DONE, status.getState());
        assertNull(status.getException());

        assertEquals(1, metadataStorage.size());
    }

}