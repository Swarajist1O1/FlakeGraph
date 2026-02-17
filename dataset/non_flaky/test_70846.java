class DummyClass_70846 {
    @Test
    public void testSlowTaskStart() throws Exception {
        final CountDownLatch startupLatch = new CountDownLatch(1);
        final CountDownLatch finishStartupLatch = new CountDownLatch(1);

        createWorkerTask();

        sourceTask.initialize(EasyMock.anyObject(SourceTaskContext.class));
        EasyMock.expectLastCall();
        sourceTask.start(TASK_PROPS);
        EasyMock.expectLastCall().andAnswer(new IAnswer<Object>() {
            @Override
            public Object answer() throws Throwable {
                startupLatch.countDown();
                assertTrue(awaitLatch(finishStartupLatch));
                return null;
            }

}