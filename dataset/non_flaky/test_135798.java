class DummyClass_135798 {
    @Test
    public void testEmptyStreamProducesZeros() {
        HystrixCommandKey key = HystrixCommandKey.Factory.asKey("CMD-CumulativeCounter-A");
        stream = CumulativeCommandEventCounterStream.getInstance(key, 10, 500);
        stream.startCachingStreamValuesIfUnstarted();

        final CountDownLatch latch = new CountDownLatch(1);
        stream.observe().take(5).subscribe(getSubscriber(latch));

        //no writes

        try {
            assertTrue(latch.await(10000, TimeUnit.MILLISECONDS));
        } catch (InterruptedException ex) {
            fail("Interrupted ex");
        }
        assertEquals(HystrixEventType.values().length, stream.getLatest().length);
        assertFalse(hasData(stream.getLatest()));
    }

}