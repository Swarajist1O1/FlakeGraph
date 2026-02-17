class DummyClass_99779 {
    @Test
    public void testQueueWaitLatency()
    {
        int latency = 100;
        Verb verb = Verb.MUTATION_REQ;

        Map<Verb, Timer> queueWaitLatency = MessagingService.instance().metrics.internalLatency;
        MessagingService.instance().metrics.recordInternalLatency(verb, latency, MILLISECONDS);
        assertEquals(1, queueWaitLatency.get(verb).getCount());
        long expectedBucket = bucketOffsets[Math.abs(Arrays.binarySearch(bucketOffsets, MILLISECONDS.toNanos(latency))) - 1];
        assertEquals(expectedBucket, queueWaitLatency.get(verb).getSnapshot().getMax());
    }

}