class DummyClass_133916 {
    @Test
    public void testGetRunningQueueItem() throws InterruptedException {
        IntegerResponse job1 = api.jobsApi().build(null,"QueueTest");
        assertNotNull(job1);
        assertTrue(job1.errors().size() == 0);
        IntegerResponse job2 = api.jobsApi().build(null,"QueueTest");
        assertNotNull(job2);
        assertTrue(job2.errors().size() == 0);

        // job1 runs first, so we get its queueItem
        QueueItem queueItem = getRunningQueueItem(job1.value());

        // If null, it means the queueItem has been cancelled, which would not be normal in this test
        assertNotNull(queueItem);
        assertFalse(queueItem.cancelled());

        //  We exepect this build to run, consequently:
        //  * the why field should now be null
        //  * the executable field should NOT be null
        //  * the build number should be set to an integer
        //  * the url for the build should be set to a string
        assertNull(queueItem.why());
        assertNotNull(queueItem.executable());
    }

}