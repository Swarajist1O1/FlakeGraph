class DummyClass_26187 {
    @Test
    public void testRunOnceJobRemovedOnFailure()
    {
        StateJob job = new StateJob(ScheduledJob.Priority.LOW, ScheduledJob.State.FAILED);
        StateJob job2 = new StateJob(ScheduledJob.Priority.LOW, ScheduledJob.State.RUNNABLE);

        queue.add(job);
        queue.add(job2);

        for (ScheduledJob next : queue)
        {
            assertThat(next.getState()).isEqualTo(ScheduledJob.State.RUNNABLE);
        }

        assertThat(queue.size()).isEqualTo(1);
        assertThat(queue.iterator()).toIterable().containsExactly(job2);
    }

}