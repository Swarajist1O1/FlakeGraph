class DummyClass_70836 {
    @Test
    public void testPollReturnsNoRecords() throws Exception {
        // Test that the task handles an empty list of records
        createWorkerTask();

        sourceTask.initialize(EasyMock.anyObject(SourceTaskContext.class));
        EasyMock.expectLastCall();
        sourceTask.start(TASK_PROPS);
        EasyMock.expectLastCall();
        statusListener.onStartup(taskId);
        EasyMock.expectLastCall();

        // We'll wait for some data, then trigger a flush
        final CountDownLatch pollLatch = expectEmptyPolls(1, new AtomicInteger());
        expectOffsetFlush(true);

        sourceTask.stop();
        EasyMock.expectLastCall();
        expectOffsetFlush(true);

        statusListener.onShutdown(taskId);
        EasyMock.expectLastCall();

        producer.close(EasyMock.anyObject(Duration.class));
        EasyMock.expectLastCall();

        transformationChain.close();
        EasyMock.expectLastCall();

        PowerMock.replayAll();

        workerTask.initialize(TASK_CONFIG);
        Future<?> taskFuture = executor.submit(workerTask);

        assertTrue(awaitLatch(pollLatch));
        assertTrue(workerTask.commitOffsets());
        workerTask.stop();
        assertTrue(workerTask.awaitStop(1000));

        taskFuture.get();
        assertPollMetrics(0);

        PowerMock.verifyAll();
    }

}