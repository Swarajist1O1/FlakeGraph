class DummyClass_135793 {
    @Test
    public void testBatchesAgeOut() {
        HystrixCollapserKey key = HystrixCollapserKey.Factory.asKey("Collapser-Batch-Size-B");
        stream = RollingCollapserBatchSizeDistributionStream.getInstance(key, 10, 100);
        stream.startCachingStreamValuesIfUnstarted();

        final CountDownLatch latch = new CountDownLatch(1);
        stream.observe().take(30).subscribe(new Subscriber<CachedValuesHistogram>() {
            @Override
            public void onCompleted() {
                latch.countDown();
            }

}