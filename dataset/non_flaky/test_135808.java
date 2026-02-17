class DummyClass_135808 {
    @Test
    public void testFallbackMissing() {
        HystrixCommandKey key = HystrixCommandKey.Factory.asKey("CMD-CumulativeCounter-K");
        stream = CumulativeCommandEventCounterStream.getInstance(key, 10, 500);
        stream.startCachingStreamValuesIfUnstarted();

        final CountDownLatch latch = new CountDownLatch(1);
        stream.observe().take(5).subscribe(getSubscriber(latch));

        Command cmd = Command.from(groupKey, key, HystrixEventType.FAILURE, 20, HystrixEventType.FALLBACK_MISSING);

        cmd.observe();

        try {
            assertTrue(latch.await(10000, TimeUnit.MILLISECONDS));
        } catch (InterruptedException ex) {
            fail("Interrupted ex");
        }
        assertEquals(HystrixEventType.values().length, stream.getLatest().length);
        long[] expected = new long[HystrixEventType.values().length];
        expected[HystrixEventType.FAILURE.ordinal()] = 1;
        expected[HystrixEventType.FALLBACK_MISSING.ordinal()] = 1;
        expected[HystrixEventType.EXCEPTION_THROWN.ordinal()] = 1;
        assertArrayEquals(expected, stream.getLatest());
    }

}