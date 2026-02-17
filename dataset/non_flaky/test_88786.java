class DummyClass_88786 {
    @Test
    public void testCallProcessIsDone() {
        UUID procId = UUID.randomUUID();

        Future<?> fut = mock(Future.class);
        doReturn(true).when(fut).isDone();
        metadataStorage.put(procId, fut);

        LongRunningProcessClearTask clearTask = createTask(procId);

        List<LongRunningProcessStatus> statuses = clearTask.call();

        assertEquals(1, statuses.size());

        LongRunningProcessStatus status = statuses.get(0);
        assertEquals(LongRunningProcessState.DONE, status.getState());
        assertNull(status.getException());

        assertEquals(0, metadataStorage.size());
    }

}