class DummyClass_99777 {
    @Test
    public void testDCLatency() throws Exception
    {
        int latency = 100;
        ConcurrentHashMap<String, MessagingMetrics.DCLatencyRecorder> dcLatency = MessagingService.instance().metrics.dcLatency;
        dcLatency.clear();

        long now = System.currentTimeMillis();
        long sentAt = now - latency;
        assertNull(dcLatency.get("datacenter1"));
        addDCLatency(sentAt, now);
        assertNotNull(dcLatency.get("datacenter1"));
        assertEquals(1, dcLatency.get("datacenter1").dcLatency.getCount());
        long expectedBucket = bucketOffsets[Math.abs(Arrays.binarySearch(bucketOffsets, MILLISECONDS.toNanos(latency))) - 1];
        assertEquals(expectedBucket, dcLatency.get("datacenter1").dcLatency.getSnapshot().getMax());
    }

}