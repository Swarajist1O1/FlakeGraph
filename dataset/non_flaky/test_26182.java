class DummyClass_26182 {
    @Test
    public void testInsertDifferentPrio()
    {
        DummyJob job = new DummyJob(Priority.LOW);
        DummyJob job2 = new DummyJob(Priority.HIGH);

        queue.add(job);
        queue.add(job2);

        assertThat(queue.iterator()).toIterable().containsExactly(job2, job);
    }

}