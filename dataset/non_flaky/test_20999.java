class DummyClass_20999 {
    @Test
    public void simpleAggregatedSample() throws Exception {
        AggregationIterator iter = new AggregationIterator();
        Map<Set<Tag>, Aggregation> samples = runQuery(iter, testData2, 100);
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
                assertEquals(count == 0 ? 0.2 : (count == 10 ? 0.5 : 0.35), sample.value, 0.0001);
                count++;
            }
            assertEquals(11, count);
        }
    }

}