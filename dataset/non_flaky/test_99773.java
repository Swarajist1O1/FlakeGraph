class DummyClass_99773 {
    @Test
    public void testBackPressureSlowFlow() throws Exception
    {
        long windowSize = 6000;
        TestTimeSource timeSource = new TestTimeSource();
        TestableBackPressure strategy = new TestableBackPressure(ImmutableMap.of(HIGH_RATIO, "0.9", FACTOR, "10", FLOW, "SLOW"), timeSource, windowSize);
        RateBasedBackPressureState state1 = strategy.newState(InetAddressAndPort.getByName("127.0.0.1"));
        RateBasedBackPressureState state2 = strategy.newState(InetAddressAndPort.getByName("127.0.0.2"));
        RateBasedBackPressureState state3 = strategy.newState(InetAddressAndPort.getByName("127.0.0.3"));

        // Update incoming and outgoing rates:
        state1.incomingRate.update(50);
        state1.outgoingRate.update(100);
        state2.incomingRate.update(100);
        state2.outgoingRate.update(100);
        state3.incomingRate.update(20); // slow
        state3.outgoingRate.update(100);

        // Move time ahead:
        timeSource.sleep(windowSize, TimeUnit.MILLISECONDS);

        // Verify the slow replica rate limiting has been applied:
        Set<RateBasedBackPressureState> replicaGroup = Sets.newHashSet(state1, state2, state3);
        strategy.apply(replicaGroup, 1, TimeUnit.SECONDS);
        assertTrue(strategy.checkAcquired());
        assertTrue(strategy.checkApplied());
        assertEquals(3.0, strategy.getRateLimiterForReplicaGroup(replicaGroup).getRate(), 0.1);
    }

}