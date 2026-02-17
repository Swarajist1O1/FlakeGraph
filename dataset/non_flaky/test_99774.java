class DummyClass_99774 {
    @Test
    public void testBackPressureWithDifferentGroups() throws Exception
    {
        long windowSize = 6000;
        TestTimeSource timeSource = new TestTimeSource();
        TestableBackPressure strategy = new TestableBackPressure(ImmutableMap.of(HIGH_RATIO, "0.9", FACTOR, "10", FLOW, "SLOW"), timeSource, windowSize);
        RateBasedBackPressureState state1 = strategy.newState(InetAddressAndPort.getByName("127.0.0.1"));
        RateBasedBackPressureState state2 = strategy.newState(InetAddressAndPort.getByName("127.0.0.2"));
        RateBasedBackPressureState state3 = strategy.newState(InetAddressAndPort.getByName("127.0.0.3"));
        RateBasedBackPressureState state4 = strategy.newState(InetAddressAndPort.getByName("127.0.0.4"));

        // Update incoming and outgoing rates:
        state1.incomingRate.update(50); // this
        state1.outgoingRate.update(100);
        state2.incomingRate.update(100);
        state2.outgoingRate.update(100);
        state3.incomingRate.update(20); // this
        state3.outgoingRate.update(100);
        state4.incomingRate.update(80);
        state4.outgoingRate.update(100);

        // Move time ahead:
        timeSource.sleep(windowSize, TimeUnit.MILLISECONDS);

        // Verify the first group:
        Set<RateBasedBackPressureState> replicaGroup = Sets.newHashSet(state1, state2);
        strategy.apply(replicaGroup, 1, TimeUnit.SECONDS);
        assertTrue(strategy.checkAcquired());
        assertTrue(strategy.checkApplied());
        assertEquals(7.4, strategy.getRateLimiterForReplicaGroup(replicaGroup).getRate(), 0.1);

        // Verify the second group:
        replicaGroup = Sets.newHashSet(state3, state4);
        strategy.apply(replicaGroup, 1, TimeUnit.SECONDS);
        assertTrue(strategy.checkAcquired());
        assertTrue(strategy.checkApplied());
        assertEquals(3.0, strategy.getRateLimiterForReplicaGroup(replicaGroup).getRate(), 0.1);
    }

}