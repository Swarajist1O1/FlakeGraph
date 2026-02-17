class DummyClass_135804 {
    @Test
    public void testShortCircuited() {
        HystrixCommandKey key = HystrixCommandKey.Factory.asKey("CMD-CumulativeCounter-G");
        stream = CumulativeCommandEventCounterStream.getInstance(key, 10, 500);
        stream.startCachingStreamValuesIfUnstarted();

        final CountDownLatch latch = new CountDownLatch(1);
        stream.observe().take(5).subscribe(getSubscriber(latch));

        //3 failures in a row will trip circuit.  let bucket roll once then submit 2 requests.
        //should see 3 FAILUREs and 2 SHORT_CIRCUITs and then 5 FALLBACK_SUCCESSes

        Command failure1 = Command.from(groupKey, key, HystrixEventType.FAILURE, 20);
        Command failure2 = Command.from(groupKey, key, HystrixEventType.FAILURE, 20);
        Command failure3 = Command.from(groupKey, key, HystrixEventType.FAILURE, 20);

        Command shortCircuit1 = Command.from(groupKey, key, HystrixEventType.SUCCESS);
        Command shortCircuit2 = Command.from(groupKey, key, HystrixEventType.SUCCESS);

        failure1.observe();
        failure2.observe();
        failure3.observe();

        try {
            Thread.sleep(500);
        } catch (InterruptedException ie) {
            fail(ie.getMessage());
        }

        shortCircuit1.observe();
        shortCircuit2.observe();

        try {
            assertTrue(latch.await(10000, TimeUnit.MILLISECONDS));
        } catch (InterruptedException ex) {
            fail("Interrupted ex");
        }

        System.out.println("ReqLog : " + HystrixRequestLog.getCurrentRequest().getExecutedCommandsAsString());
        assertTrue(shortCircuit1.isResponseShortCircuited());
        assertTrue(shortCircuit2.isResponseShortCircuited());
        assertEquals(HystrixEventType.values().length, stream.getLatest().length);
        long[] expected = new long[HystrixEventType.values().length];
        expected[HystrixEventType.FAILURE.ordinal()] = 3;
        expected[HystrixEventType.SHORT_CIRCUITED.ordinal()] = 2;
        expected[HystrixEventType.FALLBACK_SUCCESS.ordinal()] = 5;
        assertArrayEquals(expected, stream.getLatest());
    }

}