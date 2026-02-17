class DummyClass_38635 {
    @Test
    public void testPolling() throws Exception {
        es.awaitEvent();
        es.reset();

        FlumeConfiguration fc = cp.getFlumeConfiguration();
        Assert.assertTrue(fc.getConfigurationErrors().isEmpty());
        AgentConfiguration ac = fc.getConfigurationFor(AGENT_NAME);
        Assert.assertNull(ac);

        addData();
        es.awaitEvent();
        es.reset();

        verifyProperties(cp);
    }

}