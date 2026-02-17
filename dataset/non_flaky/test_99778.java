class DummyClass_99778 {
    @Test
    public void testNegativeDCLatency()
    {
        MessagingMetrics.DCLatencyRecorder updater = MessagingService.instance().metrics.internodeLatencyRecorder(InetAddressAndPort.getLocalHost());

        // if clocks are off should just not track anything
        int latency = -100;

        long now = System.currentTimeMillis();
        long sentAt = now - latency;

        long count = updater.dcLatency.getCount();
        updater.accept(now - sentAt, MILLISECONDS);
        // negative value shoudln't be recorded
        assertEquals(count, updater.dcLatency.getCount());
    }

}