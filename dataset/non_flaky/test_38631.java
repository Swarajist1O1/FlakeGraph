class DummyClass_38631 {
    @Test
    public void testSinkThrowsExceptionDuringConfiguration() throws Exception {
        String agentName = "agent1";
        String sourceType = "seq";
        String channelType = "memory";
        String sinkType = UnconfigurableSink.class.getName();
        Map<String, String> properties = getProperties(agentName, sourceType,
                channelType, sinkType);
        MemoryConfigurationProvider provider =
                new MemoryConfigurationProvider(agentName, properties);
        MaterializedConfiguration config = provider.getConfiguration();
        assertEquals(config.getSourceRunners().size(), 1);
        assertEquals(config.getChannels().size(), 1);
        assertEquals(config.getSinkRunners().size(), 0);
    }

}