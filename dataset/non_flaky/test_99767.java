class DummyClass_99767 {
    @Test
    public void testBackPressureStateUpdates()
    {
        long windowSize = 6000;
        TestTimeSource timeSource = new TestTimeSource();
        RateBasedBackPressure strategy = new RateBasedBackPressure(ImmutableMap.of(HIGH_RATIO, "0.9", FACTOR, "10", FLOW, "FAST"), timeSource, windowSize);

        RateBasedBackPressureState state = strategy.newState(InetAddressAndPort.getLoopbackAddress());
        state.onMessageSent(null);
        assertEquals(0, state.incomingRate.size());
        assertEquals(0, state.outgoingRate.size());

        state = strategy.newState(InetAddressAndPort.getLoopbackAddress());
        state.onResponseReceived();
        assertEquals(1, state.incomingRate.size());
        assertEquals(1, state.outgoingRate.size());

        state = strategy.newState(InetAddressAndPort.getLoopbackAddress());
        state.onResponseTimeout();
        assertEquals(0, state.incomingRate.size());
        assertEquals(1, state.outgoingRate.size());
    }

}