class DummyClass_99780 {
    @Test
    public void testNegativeQueueWaitLatency() throws Exception
    {
        int latency = -100;
        Verb verb = Verb.MUTATION_REQ;

        Map<Verb, Timer> queueWaitLatency = MessagingService.instance().metrics.internalLatency;
        queueWaitLatency.clear();

        assertNull(queueWaitLatency.get(verb));
        MessagingService.instance().metrics.recordInternalLatency(verb, latency, MILLISECONDS);
        assertNull(queueWaitLatency.get(verb));
    }

}