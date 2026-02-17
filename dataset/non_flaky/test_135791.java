class DummyClass_135791 {
    @Test
    public void testEmptyStreamProducesEmptyDistributions() {
        HystrixCollapserKey key = HystrixCollapserKey.Factory.asKey("Collapser-Batch-Size-A");
        stream = RollingCollapserBatchSizeDistributionStream.getInstance(key, 10, 100);
        stream.startCachingStreamValuesIfUnstarted();

        final CountDownLatch latch = new CountDownLatch(1);
        stream.observe().skip(10).take(10).subscribe(new Subscriber<CachedValuesHistogram>() {
            @Override
            public void onCompleted() {
                latch.countDown();
            }

}