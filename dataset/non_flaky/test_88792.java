class DummyClass_88792 {
    @Test
    public void testCall() throws ExecutionException, InterruptedException {
        LongRunningProcess proc = new LongRunningProcess(UUID.randomUUID(), () -> {});
        LongRunningProcessStartTask task = createTask(proc);
        List<UUID> procIds = task.call();

        assertEquals(1, procIds.size());

        UUID procId = procIds.get(0);

        assertNotNull(metadataStorage.get(procId));

        Future<?> fut = metadataStorage.get(procId);
        fut.get();

        assertEquals(true, fut.isDone());
    }

}