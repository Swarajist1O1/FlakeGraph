class DummyClass_133914 {
    @Test
    public void testGetQueue() {
        IntegerResponse job1 = api.jobsApi().build(null, "QueueTest");
        assertNotNull(job1);
        assertTrue(job1.errors().size() == 0);
        IntegerResponse job2 = api.jobsApi().build(null, "QueueTest");
        assertNotNull(job2);
        assertTrue(job2.errors().size() == 0);
        List<QueueItem> queueItems = api().queue();
        assertTrue(queueItems.size() > 0);
        boolean foundLastKickedJob = false;
        for (QueueItem item : queueItems) {
            if (item.id() == job2.value()) {
                foundLastKickedJob = true;
                break;
            }
        }
        assertTrue(foundLastKickedJob);
    }

}