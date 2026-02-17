class DummyClass_135806 {
    @Test
    public void testThreadPoolRejected() {
        HystrixCommandKey key = HystrixCommandKey.Factory.asKey("CMD-CumulativeCounter-I");
        stream = CumulativeCommandEventCounterStream.getInstance(key, 10, 500);
        stream.startCachingStreamValuesIfUnstarted();

        final CountDownLatch latch = new CountDownLatch(1);
        stream.observe().take(5).subscribe(getSubscriber(latch));

        //10 commands will saturate threadpools when called concurrently.
        //submit 2 more requests and they should be THREADPOOL_REJECTED
        //should see 10 SUCCESSes, 2 THREADPOOL_REJECTED and 2 FALLBACK_SUCCESSes

        List<Command> saturators = new ArrayList<Command>();

        for (int i = 0; i < 10; i++) {
            saturators.add(Command.from(groupKey, key, HystrixEventType.SUCCESS, 500));
        }

        Command rejected1 = Command.from(groupKey, key, HystrixEventType.SUCCESS, 0);
        Command rejected2 = Command.from(groupKey, key, HystrixEventType.SUCCESS, 0);

        for (final Command saturator : saturators) {
            saturator.observe();
        }

        try {
            Thread.sleep(100);
        } catch (InterruptedException ie) {
            fail(ie.getMessage());
        }

        rejected1.observe();
        rejected2.observe();

        try {
            assertTrue(latch.await(10000, TimeUnit.MILLISECONDS));
        } catch (InterruptedException ex) {
            fail("Interrupted ex");
        }

        System.out.println("ReqLog : " + HystrixRequestLog.getCurrentRequest().getExecutedCommandsAsString());
        assertTrue(rejected1.isResponseThreadPoolRejected());
        assertTrue(rejected2.isResponseThreadPoolRejected());
        assertEquals(HystrixEventType.values().length, stream.getLatest().length);
        long[] expected = new long[HystrixEventType.values().length];
        expected[HystrixEventType.SUCCESS.ordinal()] = 10;
        expected[HystrixEventType.THREAD_POOL_REJECTED.ordinal()] = 2;
        expected[HystrixEventType.FALLBACK_SUCCESS.ordinal()] = 2;
        assertArrayEquals(expected, stream.getLatest());
    }

}