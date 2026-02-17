class DummyClass_26193 {
    @Test
    public void testRunningOneJobWithThrowingLock() throws LockException
    {
        DummyJob job = new DummyJob(ScheduledJob.Priority.LOW);
        myScheduler.schedule(job);

        when(myLockFactory.tryLock(any(), anyString(), anyInt(), anyMap())).thenThrow(new LockException(""));

        myScheduler.run();

        assertThat(job.hasRun()).isFalse();
        assertThat(myScheduler.getQueueSize()).isEqualTo(1);
    }

}