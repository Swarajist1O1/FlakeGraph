class DummyClass_26194 {
    @Test (timeout = 2000L)
    public void testRunningTwoJobsInParallelShouldFail() throws InterruptedException
    {
        LongRunningJob job = new LongRunningJob(ScheduledJob.Priority.HIGH);
        LongRunningJob job2 = new LongRunningJob(ScheduledJob.Priority.LOW);
        myScheduler.schedule(job);
        myScheduler.schedule(job2);

        final CountDownLatch cdl = new CountDownLatch(1);

        new Thread()
        {

            @Override
            public void run()
            {
                myScheduler.run();
                cdl.countDown();
            }

}