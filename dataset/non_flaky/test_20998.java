class DummyClass_20998 {
    @Test
    public void simpleGetOneSample() throws Exception {
        // check that data gets pulled out
        AggregationIterator iter = new AggregationIterator();
        Map<Set<Tag>, Aggregation> samples = runQuery(iter, testData1, 100);
        assertEquals(1, samples.size());
        for (Entry<Set<Tag>, Aggregation> entry : samples.entrySet()) {
            Set<Tag> tags = entry.getKey();
            assertEquals(1, tags.size());
            assertEquals(Collections.singleton(new Tag("host", ".*")), tags);
            long ts = 0;
            int count = 0;
            for (Sample sample : entry.getValue()) {
                assertEquals(ts, sample.timestamp);
                ts += 100;
                assertEquals(0.2, sample.value, 0.0001);
                count++;
            }
            assertEquals(1000, ts);
            assertEquals(10, count);
        }
    }

}