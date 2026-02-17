class DummyClass_21005 {
    @Test
    public void testDownsampleCombining() throws Exception {

        int numTagVariations = 2;
        int sampleInterval = 50;
        int elapsedTime = 100;
        int skipInterval = 10;
        SortedMap<Key, Value> testData3 = createTestData3(elapsedTime, skipInterval, numTagVariations);
        DownsampleIterator iter = new DownsampleIterator();
        Map<Set<Tag>, Downsample> samples = runQuery(iter, testData3, sampleInterval, 1000);
        assertEquals(numTagVariations, samples.size());
        long totalBuckets = 0;
        for (Entry<Set<Tag>, Downsample> entry : samples.entrySet()) {
            totalBuckets = totalBuckets + entry.getValue().getNumBuckets();
        }
        assertEquals((elapsedTime / sampleInterval) * numTagVariations, totalBuckets);
    }

}