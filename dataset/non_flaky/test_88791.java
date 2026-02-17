class DummyClass_88791 {
    @Test
    public void testCallProcessIsDoneWithException() throws ExecutionException, InterruptedException {
        UUID procId = UUID.randomUUID();

        Future<?> fut = mock(Future.class);
        doReturn(true).when(fut).isDone();
        doThrow(RuntimeException.class).when(fut).get();
        metadataStorage.put(procId, fut);

        LongRunningProcessStopTask stopTask = createTask(procId, true);

        List<LongRunningProcessStatus> statuses = stopTask.call();

        assertEquals(1, statuses.size());
        verify(fut).cancel(eq(true));

        LongRunningProcessStatus status = statuses.get(0);
        assertEquals(LongRunningProcessState.DONE, status.getState());
        assertNotNull(status.getException());
        assertTrue(status.getException() instanceof RuntimeException);

        assertEquals(0, metadataStorage.size());
    }

}