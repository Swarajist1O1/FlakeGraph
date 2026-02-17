class DummyClass_133918 {
    @Test
    public void testQueueItemMultipleParameters() throws InterruptedException {
        Map<String, List<String>> params = new HashMap<>();
        params.put("SomeKey1", Lists.newArrayList("SomeVeryNewValue1"));
        IntegerResponse job1 = api.jobsApi().buildWithParameters(null, "QueueTestMultipleParams",params);
        assertNotNull(job1);
        assertTrue(job1.value() > 0);
        assertTrue(job1.errors().size() == 0);

        // Jenkins will reject two consecutive build requests when the build parameter values are the same
        // So we must set some different parameter values
        params = new HashMap<>();
        params.put("SomeKey1", Lists.newArrayList("SomeVeryNewValue2"));
        IntegerResponse job2 = api.jobsApi().buildWithParameters(null, "QueueTestMultipleParams", params);
        assertNotNull(job2);
        assertTrue(job2.value() > 0);
        assertTrue(job2.errors().size() == 0);

        QueueItem queueItem = getRunningQueueItem(job1.value());
        assertNotNull(queueItem);
        assertFalse(queueItem.cancelled());

        Map <String, String> map = Maps.newHashMap();
        map.put("SomeKey1", "SomeVeryNewValue1");
        map.put("SomeKey2", "SomeValue2");
        map.put("SomeKey3", "SomeValue3");
        assertEquals(queueItem.params(), map);
    }

}