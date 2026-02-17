class DummyClass_99775 {
    @Test
    public void testBackPressurePastTimeout() throws Exception
    {
        long windowSize = 10000;
        TestTimeSource timeSource = new TestTimeSource();
        TestableBackPressure strategy = new TestableBackPressure(ImmutableMap.of(HIGH_RATIO, "0.9", FACTOR, "10", FLOW, "SLOW"), timeSource, windowSize);
        RateBasedBackPressureState state1 = strategy.newState(InetAddressAndPort.getByName("127.0.0.1"));
        RateBasedBackPressureState state2 = strategy.newState(InetAddressAndPort.getByName("127.0.0.2"));
        RateBasedBackPressureState state3 = strategy.newState(InetAddressAndPort.getByName("127.0.0.3"));

        // Update incoming and outgoing rates:
        state1.incomingRate.update(5); // slow
        state1.outgoingRate.update(100);
        state2.incomingRate.update(100);
        state2.outgoingRate.update(100);
        state3.incomingRate.update(100);
        state3.outgoingRate.update(100);

        // Move time ahead:
        timeSource.sleep(windowSize, TimeUnit.MILLISECONDS);

        // Verify the slow replica rate limiting has been applied:
        Set<RateBasedBackPressureState> replicaGroup = Sets.newHashSet(state1, state2, state3);
        strategy.apply(replicaGroup, 4, TimeUnit.SECONDS);
        assertTrue(strategy.checkAcquired());
        assertTrue(strategy.checkApplied());
        assertEquals(0.5, strategy.getRateLimiterForReplicaGroup(replicaGroup).getRate(), 0.1);

        // Make one more apply call to saturate the rate limit timeout (0.5 requests per second means 2 requests span
        // 4 seconds, but we can only make one as we have to subtract the incoming response time):
        strategy.apply(replicaGroup, 4, TimeUnit.SECONDS);

        // Now verify another call to apply doesn't acquire the rate limit because of the max timeout of 4 seconds minus
        // 2 seconds of response time, so the time source itself sleeps two second:
        long start = timeSource.currentTimeMillis();
        strategy.apply(replicaGroup, 4, TimeUnit.SECONDS);
        assertFalse(strategy.checkAcquired());
        assertTrue(strategy.checkApplied());
        assertEquals(TimeUnit.NANOSECONDS.convert(2, TimeUnit.SECONDS),
                     strategy.timeout);
        assertEquals(strategy.timeout,
                     TimeUnit.NANOSECONDS.convert(timeSource.currentTimeMillis() - start, TimeUnit.MILLISECONDS));
    }

}