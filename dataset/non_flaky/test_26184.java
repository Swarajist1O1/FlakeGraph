class DummyClass_26184 {
    @Test
    public void testNonRunnableQueueIsEmpty() throws ScheduledJobException
    {
        final int nJobs = 10;

        for (int i = 0; i < nJobs; i++)
        {
            queue.add(new RunnableOnce(Priority.LOW));
        }

        for (ScheduledJob job : queue)
        {
            job.postExecute(true, null);
        }

        assertThat(queue.iterator()).toIterable().isEmpty();
    }

}