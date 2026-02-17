class DummyClass_135796 {
    @Test
    public void testCollapsedAndResponseFromCache() {
        HystrixCollapserKey key = HystrixCollapserKey.Factory.asKey("CumulativeCollapser-C");
        stream = CumulativeCollapserEventCounterStream.getInstance(key, 10, 100);
        stream.startCachingStreamValuesIfUnstarted();

        final CountDownLatch latch = new CountDownLatch(1);
        stream.observe().take(10).subscribe(getSubscriber(latch));

        for (int i = 0; i < 3; i++) {
            CommandStreamTest.Collapser.from(key, i).observe();
            CommandStreamTest.Collapser.from(key, i).observe(); //same arg - should get a response from cache
            CommandStreamTest.Collapser.from(key, i).observe(); //same arg - should get a response from cache
        }

        try {
            assertTrue(latch.await(10000, TimeUnit.MILLISECONDS));
        } catch (InterruptedException ex) {
            fail("Interrupted ex");
        }
        assertEquals(HystrixEventType.Collapser.values().length, stream.getLatest().length);
        long[] expected = new long[HystrixEventType.Collapser.values().length];
        expected[HystrixEventType.Collapser.BATCH_EXECUTED.ordinal()] = 1;
        expected[HystrixEventType.Collapser.ADDED_TO_BATCH.ordinal()] = 3;
        expected[HystrixEventType.Collapser.RESPONSE_FROM_CACHE.ordinal()] = 6;
        System.out.println("ReqLog : " + HystrixRequestLog.getCurrentRequest().getExecutedCommandsAsString());
        assertArrayEquals(expected, stream.getLatest());
    }

}