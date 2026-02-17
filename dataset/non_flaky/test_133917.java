class DummyClass_133917 {
    @Test
    public void testQueueItemSingleParameters() throws InterruptedException {
        Map<String, List<String>> params = new HashMap<>();
        params.put("SomeKey", Lists.newArrayList("SomeVeryNewValue1"));
        IntegerResponse job1 = api.jobsApi().buildWithParameters(null,"QueueTestSingleParam", params);
        assertNotNull(job1);
        assertTrue(job1.value() > 0);
        assertTrue(job1.errors().size() == 0);

        // Jenkins will reject two consecutive build requests when the build parameter values are the same
        // So we must set some different parameter values
        params = new HashMap<>();
        params.put("SomeKey", Lists.newArrayList("SomeVeryNewValue2"));
        IntegerResponse job2 = api.jobsApi().buildWithParameters(null,"QueueTestSingleParam", params);
        assertNotNull(job2);
        assertTrue(job2.value() > 0);
        assertTrue(job2.errors().size() == 0);

        QueueItem queueItem = getRunningQueueItem(job1.value());
        assertNotNull(queueItem);
        assertFalse(queueItem.cancelled());

        Map <String, String> map = Maps.newHashMap();
        map.put("SomeKey", "SomeVeryNewValue1");
        assertEquals(queueItem.params(), map);
    }

}