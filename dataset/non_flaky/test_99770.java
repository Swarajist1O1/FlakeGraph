class DummyClass_99770 {
    @Test
    public void testBackPressureWhenBelowHighRatio() throws Exception
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

        // Verify the rate is decreased by factor:
        strategy.apply(Sets.newHashSet(state), 1, TimeUnit.SECONDS);
        assertEquals(7.4, state.rateLimiter.getRate(), 0.1);
    }

}