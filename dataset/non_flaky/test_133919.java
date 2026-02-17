class DummyClass_133919 {
    @Test
    public void testQueueItemEmptyParameterValue() throws InterruptedException {
        Map<String, List<String>> params = new HashMap<>();
        params.put("SomeKey1", Lists.newArrayList(""));
        IntegerResponse job1 = api.jobsApi().buildWithParameters(null, "QueueTestMultipleParams",params);
        assertNotNull(job1);
        assertTrue(job1.value() > 0);
        assertTrue(job1.errors().size() == 0);

        QueueItem queueItem = getRunningQueueItem(job1.value());
        assertNotNull(queueItem);

        Map <String, String> map = Maps.newHashMap();
        map.put("SomeKey1", "");
        map.put("SomeKey2", "SomeValue2");
        map.put("SomeKey3", "SomeValue3");
        assertEquals(queueItem.params(), map);
    }

}