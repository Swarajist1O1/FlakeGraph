class DummyClass_99768 {
    @Test
    public void testBackPressureIsNotUpdatedBeyondInfinity() throws Exception
    {
        long windowSize = 6000;
        TestTimeSource timeSource = new TestTimeSource();
        RateBasedBackPressure strategy = new RateBasedBackPressure(ImmutableMap.of(HIGH_RATIO, "0.9", FACTOR, "10", FLOW, "FAST"), timeSource, windowSize);
        RateBasedBackPressureState state = strategy.newState(InetAddressAndPort.getLoopbackAddress());

        // Get initial rate:
        double initialRate = state.rateLimiter.getRate();
        assertEquals(Double.POSITIVE_INFINITY, initialRate, 0.0);

        // Update incoming and outgoing rate equally:
        state.incomingRate.update(1);
        state.outgoingRate.update(1);

        // Move time ahead:
        timeSource.sleep(windowSize, TimeUnit.MILLISECONDS);

        // Verify the rate doesn't change because already at infinity:
        strategy.apply(Sets.newHashSet(state), 1, TimeUnit.SECONDS);
        assertEquals(initialRate, state.rateLimiter.getRate(), 0.0);
    }

}