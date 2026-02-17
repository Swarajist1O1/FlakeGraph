class DummyClass_21002 {
    @Test
    public void simpleTestDownsampling() throws Exception {
        DownsampleIterator iter = new DownsampleIterator();
        Map<Set<Tag>, Downsample> samples = runQuery(iter, testData2, 200, -1);
        assertEquals(2, samples.size());
        for (Tag tag : new Tag[] { new Tag("host", "host1"), new Tag("host", "host2") }) {
            Downsample dsample = samples.get(Collections.singleton(tag));
            assertNotNull(dsample);
            long ts = 0;
            double value = .2;
            if (tag.getValue().equals("host2")) {
                value = .5;
            }
            int count = 0;
            for (Sample sample : dsample) {
                assertEquals(ts, sample.timestamp);
                ts += 200;
                assertEquals(value, sample.value, 0.0001);
                count++;
            }
            assertEquals(5, count);
        }
    }

}