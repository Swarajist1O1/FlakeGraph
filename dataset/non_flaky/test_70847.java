class DummyClass_70847 {
    @Test
    public void testCancel() {
        createWorkerTask();

        offsetReader.close();
        PowerMock.expectLastCall();

        PowerMock.replayAll();

        workerTask.cancel();

        PowerMock.verifyAll();
    }

}