class DummyClass_38630 {
    @Test
    public void testChannelThrowsExceptionDuringConfiguration() throws Exception {
        String agentName = "agent1";
        String sourceType = "seq";
        String channelType = UnconfigurableChannel.class.getName();
        String sinkType = "null";
        Map<String, String> properties = getProperties(agentName, sourceType,
                channelType, sinkType);
        MemoryConfigurationProvider provider =
                new MemoryConfigurationProvider(agentName, properties);
        MaterializedConfiguration config = provider.getConfiguration();
        assertEquals(config.getSourceRunners().size(), 0);
        assertEquals(config.getChannels().size(), 0);
        assertEquals(config.getSinkRunners().size(), 0);
    }

}