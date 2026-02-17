class DummyClass_88793 {
    @Test(expected = ExecutionException.class)
    public void testCallWithException() throws ExecutionException, InterruptedException {
        LongRunningProcess proc = new LongRunningProcess(UUID.randomUUID(), () -> {
            throw new RuntimeException();
        });
        LongRunningProcessStartTask task = createTask(proc);
        List<UUID> procIds = task.call();

        assertEquals(1, procIds.size());

        UUID procId = procIds.get(0);

        assertNotNull(metadataStorage.get(procId));

        Future<?> fut = metadataStorage.get(procId);
        fut.get();
    }

}