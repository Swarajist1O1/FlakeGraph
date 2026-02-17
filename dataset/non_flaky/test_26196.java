class DummyClass_26196 {
    @Test
    public void testTwoJobsThrowingLock() throws LockException
    {
        DummyJob job = new DummyJob(ScheduledJob.Priority.LOW);
        DummyJob job2 = new DummyJob(ScheduledJob.Priority.LOW);
        myScheduler.schedule(job);
        myScheduler.schedule(job2);

        when(myLockFactory.tryLock(any(), anyString(), anyInt(), anyMap())).thenThrow(new LockException(""));

        myScheduler.run();

        assertThat(job.hasRun()).isFalse();
        assertThat(myScheduler.getQueueSize()).isEqualTo(2);
        verify(myLockFactory, times(2)).tryLock(any(), anyString(), anyInt(), anyMap());
    }

}