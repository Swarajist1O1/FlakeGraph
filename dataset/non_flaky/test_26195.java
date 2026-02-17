class DummyClass_26195 {
    @Test
    public void testTwoJobsRejected()
    {
        DummyJob job = new DummyJob(ScheduledJob.Priority.LOW);
        DummyJob job2 = new DummyJob(ScheduledJob.Priority.LOW);
        myScheduler.schedule(job);
        myScheduler.schedule(job2);

        when(myRunPolicy.validate(any(ScheduledJob.class))).thenReturn(1L);

        myScheduler.run();

        assertThat(job.hasRun()).isFalse();
        assertThat(myScheduler.getQueueSize()).isEqualTo(2);
        verify(myRunPolicy, times(2)).validate(any(ScheduledJob.class));
    }

}