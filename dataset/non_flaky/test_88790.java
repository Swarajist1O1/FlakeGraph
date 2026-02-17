class DummyClass_88790 {
    @Test
    public void testCallProcessIsDone() {
        UUID procId = UUID.randomUUID();

        Future<?> fut = mock(Future.class);
        doReturn(true).when(fut).isDone();
        metadataStorage.put(procId, fut);

        LongRunningProcessStopTask stopTask = createTask(procId, true);

        List<LongRunningProcessStatus> statuses = stopTask.call();

        assertEquals(1, statuses.size());
        verify(fut).cancel(eq(true));

        LongRunningProcessStatus status = statuses.get(0);
        assertEquals(LongRunningProcessState.DONE, status.getState());
        assertNull(status.getException());

        assertEquals(0, metadataStorage.size());
    }

}