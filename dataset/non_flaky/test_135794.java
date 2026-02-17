class DummyClass_135794 {
    @Test
    public void testEmptyStreamProducesZeros() {
        HystrixCollapserKey key = HystrixCollapserKey.Factory.asKey("CumulativeCollapser-A");
        stream = CumulativeCollapserEventCounterStream.getInstance(key, 10, 100);
        stream.startCachingStreamValuesIfUnstarted();

        final CountDownLatch latch = new CountDownLatch(1);
        stream.observe().take(10).subscribe(getSubscriber(latch));

        //no writes

        try {
            assertTrue(latch.await(10000, TimeUnit.MILLISECONDS));
        } catch (InterruptedException ex) {
            fail("Interrupted ex");
        }
        System.out.println("ReqLog : " + HystrixRequestLog.getCurrentRequest().getExecutedCommandsAsString());
        assertEquals(HystrixEventType.Collapser.values().length, stream.getLatest().length);
        assertEquals(0, stream.getLatest(HystrixEventType.Collapser.ADDED_TO_BATCH));
        assertEquals(0, stream.getLatest(HystrixEventType.Collapser.BATCH_EXECUTED));
        assertEquals(0, stream.getLatest(HystrixEventType.Collapser.RESPONSE_FROM_CACHE));
    }

}