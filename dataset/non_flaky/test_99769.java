class DummyClass_99769 {
    @Test
    public void testBackPressureIsUpdatedOncePerWindowSize() throws Exception
    {
        long windowSize = 6000;
        TestTimeSource timeSource = new TestTimeSource();
        RateBasedBackPressure strategy = new RateBasedBackPressure(ImmutableMap.of(HIGH_RATIO, "0.9", FACTOR, "10", FLOW, "FAST"), timeSource, windowSize);
        RateBasedBackPressureState state = strategy.newState(InetAddressAndPort.getLoopbackAddress());

        // Get initial time:
        long current = state.getLastIntervalAcquire();
        assertEquals(0, current);

        // Update incoming and outgoing rate:
        state.incomingRate.update(1);
        state.outgoingRate.update(1);

        // Move time ahead by window size:
        timeSource.sleep(windowSize, TimeUnit.MILLISECONDS);

        // Verify the timestamp changed:
        strategy.apply(Sets.newHashSet(state), 1, TimeUnit.SECONDS);
        current = state.getLastIntervalAcquire();
        assertEquals(timeSource.currentTimeMillis(), current);

        // Move time ahead by less than interval:
        long previous = current;
        timeSource.sleep(windowSize / 2, TimeUnit.MILLISECONDS);

        // Verify the last timestamp didn't change because below the window size:
        strategy.apply(Sets.newHashSet(state), 1, TimeUnit.SECONDS);
        current = state.getLastIntervalAcquire();
        assertEquals(previous, current);
    }

}