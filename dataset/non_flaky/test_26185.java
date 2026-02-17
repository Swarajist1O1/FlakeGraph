class DummyClass_26185 {
    @Test
    public void testRemoveJobInQueueIsPossible()
    {
        DummyJob job = new DummyJob(Priority.HIGH);
        DummyJob job2 = new DummyJob(Priority.LOW);

        queue.add(job);
        queue.add(job2);

        Iterator<ScheduledJob> iterator = queue.iterator();

        queue.remove(job2);

        assertThat(iterator).toIterable().containsExactly(job, job2);
        assertThat(queue.iterator()).toIterable().containsExactly(job);
    }

}