class DummyClass_26189 {
    @Test
    public void testRunningOneJob()
    {
        DummyJob job = new DummyJob(ScheduledJob.Priority.LOW);
        myScheduler.schedule(job);

        myScheduler.run();

        assertThat(job.hasRun()).isTrue();
        assertThat(myScheduler.getQueueSize()).isEqualTo(1);
    }

}