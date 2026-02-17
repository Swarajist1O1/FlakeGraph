class DummyClass_26198 {
    @Test (timeout = 2000L)
    public void testRemoveLongRunningJob() throws InterruptedException
    {
        LongRunningJob job = new LongRunningJob(ScheduledJob.Priority.HIGH);
        myScheduler.schedule(job);

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