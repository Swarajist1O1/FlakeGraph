class DummyClass_99772 {
    @Test
    public void testBackPressureFastFlow() throws Exception
    {
        long windowSize = 6000;
        TestTimeSource timeSource = new TestTimeSource();
        TestableBackPressure strategy = new TestableBackPressure(ImmutableMap.of(HIGH_RATIO, "0.9", FACTOR, "10", FLOW, "FAST"), timeSource, windowSize);
        RateBasedBackPressureState state1 = strategy.newState(InetAddressAndPort.getByName("127.0.0.1"));
        RateBasedBackPressureState state2 = strategy.newState(InetAddressAndPort.getByName("127.0.0.2"));
        RateBasedBackPressureState state3 = strategy.newState(InetAddressAndPort.getByName("127.0.0.3"));

        // Update incoming and outgoing rates:
        state1.incomingRate.update(50);
        state1.outgoingRate.update(100);
        state2.incomingRate.update(80); // fast
        state2.outgoingRate.update(100);
        state3.incomingRate.update(20);
        state3.outgoingRate.update(100);

        // Move time ahead:
        timeSource.sleep(windowSize, TimeUnit.MILLISECONDS);

        // Verify the fast replica rate limiting has been applied:
        Set<RateBasedBackPressureState> replicaGroup = Sets.newHashSet(state1, state2, state3);
        strategy.apply(replicaGroup, 1, TimeUnit.SECONDS);
        assertTrue(strategy.checkAcquired());
        assertTrue(strategy.checkApplied());
        assertEquals(12.0, strategy.getRateLimiterForReplicaGroup(replicaGroup).getRate(), 0.1);
    }

}