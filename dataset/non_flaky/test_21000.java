class DummyClass_21000 {
    @Test
    public void simpleGetOneSample() throws Exception {
        // check that data gets pulled out
        DownsampleIterator iter = new DownsampleIterator();
        Map<Set<Tag>, Downsample> samples = runQuery(iter, testData1, 100, -1);
        assertEquals(1, samples.size());
        for (Entry<Set<Tag>, Downsample> entry : samples.entrySet()) {
            Set<Tag> tags = entry.getKey();
            assertEquals(1, tags.size());
            assertEquals(Collections.singleton(new Tag("host", "host1")), tags);
            long ts = 0;
            for (Sample sample : entry.getValue()) {
                assertEquals(ts, sample.timestamp);
                ts += 100;
                assertEquals(0.2, sample.value, 0.0001);
            }
            assertEquals(1000, ts);
        }
    }

}