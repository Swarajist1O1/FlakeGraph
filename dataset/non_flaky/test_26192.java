class DummyClass_26192 {
    @Test
    public void testRunningJobWithThrowingRunPolicy()
    {
        DummyJob job = new DummyJob(ScheduledJob.Priority.LOW);
        myScheduler.schedule(job);

        when(myRunPolicy.validate(any(ScheduledJob.class))).thenThrow(new IllegalStateException());

        myScheduler.run();

        assertThat(job.hasRun()).isFalse();
        assertThat(myScheduler.getQueueSize()).isEqualTo(1);
    }

}