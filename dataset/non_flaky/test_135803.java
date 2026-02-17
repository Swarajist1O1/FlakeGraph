class DummyClass_135803 {
    @Test
    public void testRequestFromCache() {
        HystrixCommandKey key = HystrixCommandKey.Factory.asKey("CMD-CumulativeCounter-F");
        stream = CumulativeCommandEventCounterStream.getInstance(key, 10, 500);
        stream.startCachingStreamValuesIfUnstarted();

        final CountDownLatch latch = new CountDownLatch(1);
        stream.observe().take(5).subscribe(getSubscriber(latch));

        Command cmd1 = Command.from(groupKey, key, HystrixEventType.SUCCESS, 20);
        Command cmd2 = Command.from(groupKey, key, HystrixEventType.RESPONSE_FROM_CACHE);
        Command cmd3 = Command.from(groupKey, key, HystrixEventType.RESPONSE_FROM_CACHE);

        cmd1.observe();
        cmd2.observe();
        cmd3.observe();

        try {
            assertTrue(latch.await(10000, TimeUnit.MILLISECONDS));
        } catch (InterruptedException ex) {
            fail("Interrupted ex");
        }

        System.out.println("ReqLog : " + HystrixRequestLog.getCurrentRequest().getExecutedCommandsAsString());
        assertEquals(HystrixEventType.values().length, stream.getLatest().length);
        long[] expected = new long[HystrixEventType.values().length];
        expected[HystrixEventType.SUCCESS.ordinal()] = 1;
        expected[HystrixEventType.RESPONSE_FROM_CACHE.ordinal()] = 2;
        assertArrayEquals(expected, stream.getLatest());
    }

}