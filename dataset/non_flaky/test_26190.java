class DummyClass_26190 {
    @Test
    public void testRunningJobWithFailingRunPolicy()
    {
        DummyJob job = new DummyJob(ScheduledJob.Priority.LOW);
        myScheduler.schedule(job);

        when(myRunPolicy.validate(any(ScheduledJob.class))).thenReturn(1L);

        myScheduler.run();

        assertThat(job.hasRun()).isFalse();
        assertThat(myScheduler.getQueueSize()).isEqualTo(1);
    }

}