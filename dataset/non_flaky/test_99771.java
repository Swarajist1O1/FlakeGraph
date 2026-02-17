class DummyClass_99771 {
    @Test
    public void testBackPressureRateLimiterIsIncreasedAfterGoingAgainAboveHighRatio() throws Exception
    {
        long windowSize = 6000;
        TestTimeSource timeSource = new TestTimeSource();
        RateBasedBackPressure strategy = new RateBasedBackPressure(ImmutableMap.of(HIGH_RATIO, "0.9", FACTOR, "10", FLOW, "FAST"), timeSource, windowSize);
        RateBasedBackPressureState state = strategy.newState(InetAddressAndPort.getLoopbackAddress());

        // Update incoming and outgoing rate so that the ratio is 0.5:
        state.incomingRate.update(50);
        state.outgoingRate.update(100);

        // Move time ahead:
        timeSource.sleep(windowSize, TimeUnit.MILLISECONDS);

        // Verify the rate decreased:
        strategy.apply(Sets.newHashSet(state), 1, TimeUnit.SECONDS);
        assertEquals(7.4, state.rateLimiter.getRate(), 0.1);

        // Update incoming and outgoing rate back above high rate:
        state.incomingRate.update(50);
        state.outgoingRate.update(50);

        // Move time ahead:
        timeSource.sleep(windowSize, TimeUnit.MILLISECONDS);

        // Verify rate limiter is increased by factor:
        strategy.apply(Sets.newHashSet(state), 1, TimeUnit.SECONDS);
        assertEquals(8.25, state.rateLimiter.getRate(), 0.1);

        // Update incoming and outgoing rate to keep it below the limiter rate:
        state.incomingRate.update(1);
        state.outgoingRate.update(1);

        // Move time ahead:
        timeSource.sleep(windowSize, TimeUnit.MILLISECONDS);

        // Verify rate limiter is not increased as already higher than the actual rate:
        strategy.apply(Sets.newHashSet(state), 1, TimeUnit.SECONDS);
        assertEquals(8.25, state.rateLimiter.getRate(), 0.1);
    }

}