class DummyClass_88797 {
    @Test
    public void testCallProcessIsDoneWithException() throws ExecutionException, InterruptedException {
        UUID procId = UUID.randomUUID();

        Future<?> fut = mock(Future.class);
        doReturn(true).when(fut).isDone();
        doThrow(RuntimeException.class).when(fut).get();
        metadataStorage.put(procId, fut);

        LongRunningProcessPingTask pingTask = createTask(procId);

        List<LongRunningProcessStatus> statuses = pingTask.call();

        assertEquals(1, statuses.size());

        LongRunningProcessStatus status = statuses.get(0);
        assertEquals(LongRunningProcessState.DONE, status.getState());
        assertNotNull(status.getException());
        assertTrue(status.getException() instanceof RuntimeException);

        assertEquals(1, metadataStorage.size());
    }

}