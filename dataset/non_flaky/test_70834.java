class DummyClass_70834 {
    @Test
    public void testPollsInBackground() throws Exception {
        createWorkerTask();

        sourceTask.initialize(EasyMock.anyObject(SourceTaskContext.class));
        EasyMock.expectLastCall();
        sourceTask.start(TASK_PROPS);
        EasyMock.expectLastCall();
        statusListener.onStartup(taskId);
        EasyMock.expectLastCall();

        final CountDownLatch pollLatch = expectPolls(10);
        // In this test, we don't flush, so nothing goes any further than the offset writer

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
        workerTask.stop();
        assertTrue(workerTask.awaitStop(1000));

        taskFuture.get();
        assertPollMetrics(10);

        PowerMock.verifyAll();
    }

}