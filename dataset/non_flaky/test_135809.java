class DummyClass_135809 {
    @Test
    public void testFallbackRejection() {
        HystrixCommandKey key = HystrixCommandKey.Factory.asKey("CMD-CumulativeCounter-L");
        stream = CumulativeCommandEventCounterStream.getInstance(key, 10, 500);
        stream.startCachingStreamValuesIfUnstarted();

        final CountDownLatch latch = new CountDownLatch(1);
        stream.observe().take(5).subscribe(getSubscriber(latch));

        //fallback semaphore size is 5.  So let 5 commands saturate that semaphore, then
        //let 2 more commands go to fallback.  they should get rejected by the fallback-semaphore

        List<Command> fallbackSaturators = new ArrayList<Command>();
        for (int i = 0; i < 5; i++) {
            fallbackSaturators.add(Command.from(groupKey, key, HystrixEventType.FAILURE, 20, HystrixEventType.FALLBACK_SUCCESS, 400));
        }

        Command rejection1 = Command.from(groupKey, key, HystrixEventType.FAILURE, 20, HystrixEventType.FALLBACK_SUCCESS, 0);
        Command rejection2 = Command.from(groupKey, key, HystrixEventType.FAILURE, 20, HystrixEventType.FALLBACK_SUCCESS, 0);

        for (Command saturator: fallbackSaturators) {
            saturator.observe();
        }

        try {
            Thread.sleep(70);
        } catch (InterruptedException ex) {
            fail(ex.getMessage());
        }

        rejection1.observe();
        rejection2.observe();

        try {
            assertTrue(latch.await(10000, TimeUnit.MILLISECONDS));
        } catch (InterruptedException ex) {
            fail("Interrupted ex");
        }
        assertEquals(HystrixEventType.values().length, stream.getLatest().length);
        long[] expected = new long[HystrixEventType.values().length];
        expected[HystrixEventType.FAILURE.ordinal()] = 7;
        expected[HystrixEventType.FALLBACK_SUCCESS.ordinal()] = 5;
        expected[HystrixEventType.FALLBACK_REJECTION.ordinal()] = 2;
        expected[HystrixEventType.EXCEPTION_THROWN.ordinal()] = 2;
        assertArrayEquals(expected, stream.getLatest());
    }

}