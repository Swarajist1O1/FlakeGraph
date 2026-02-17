class DummyClass_133915 {
    @Test
    public void testGetPendingQueueItem() {
        IntegerResponse job1 = api.jobsApi().build(null,"QueueTest");
        assertNotNull(job1);
        assertTrue(job1.errors().size() == 0);
        IntegerResponse job2 = api.jobsApi().build(null,"QueueTest");
        assertNotNull(job2);
        assertTrue(job2.errors().size() == 0);

        // job2 is queue after job1, so while job1 runs, job2 is pending in the queue
        QueueItem queueItem = api().queueItem(job2.value());
        assertFalse(queueItem.cancelled());
        assertNotNull(queueItem.why());
        assertNull(queueItem.executable());
    }

}