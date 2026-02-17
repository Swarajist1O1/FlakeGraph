class DummyClass_88785 {
    @Test(expected = IllegalStateException.class)
    public void testCallProcessIsRunning() {
        UUID procId = UUID.randomUUID();

        Future<?> fut = mock(Future.class);
        doReturn(false).when(fut).isDone();
        metadataStorage.put(procId, fut);

        LongRunningProcessClearTask clearTask = createTask(procId);

        clearTask.call();
    }

}