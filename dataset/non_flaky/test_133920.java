class DummyClass_133920 {
    @Test
    public void testGetCancelledQueueItem() throws InterruptedException {
        IntegerResponse job1 = api.jobsApi().build(null,"QueueTest");
        assertNotNull(job1);
        assertTrue(job1.errors().size() == 0);
        IntegerResponse job2 = api.jobsApi().build(null, "QueueTest");
        assertNotNull(job2);
        assertTrue(job2.errors().size() == 0);

        RequestStatus success = api().cancel(job2.value());
        assertNotNull(success);
        assertTrue(success.value());
        assertTrue(success.errors().isEmpty());

        QueueItem queueItem = api().queueItem(job2.value());
        assertTrue(queueItem.cancelled());
        assertNull(queueItem.why());
        assertNull(queueItem.executable());
    }

}