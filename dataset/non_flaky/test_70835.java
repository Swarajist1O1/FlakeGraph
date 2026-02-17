class DummyClass_70835 {
    @Test
    public void testFailureInPoll() throws Exception {
        createWorkerTask();

        sourceTask.initialize(EasyMock.anyObject(SourceTaskContext.class));
        EasyMock.expectLastCall();
        sourceTask.start(TASK_PROPS);
        EasyMock.expectLastCall();
        statusListener.onStartup(taskId);
        EasyMock.expectLastCall();

        final CountDownLatch pollLatch = new CountDownLatch(1);
        final RuntimeException exception = new RuntimeException();
        EasyMock.expect(sourceTask.poll()).andAnswer(new IAnswer<List<SourceRecord>>() {
            @Override
            public List<SourceRecord> answer() throws Throwable {
                pollLatch.countDown();
                throw exception;
            }

}