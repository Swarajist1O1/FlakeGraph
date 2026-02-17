class DummyClass_26181 {
    @Test
    public void testInsertRemoveOne()
    {
        DummyJob job = new DummyJob(Priority.LOW);

        queue.add(job);

        assertThat(queue.iterator()).toIterable().containsExactly(job);
    }

}