class DummyClass_38629 {
    @Test
    public void testSourceThrowsExceptionDuringConfiguration() throws Exception {
        String agentName = "agent1";
        String sourceType = UnconfigurableSource.class.getName();
        String channelType = "memory";
        String sinkType = "null";
        Map<String, String> properties = getProperties(agentName, sourceType,
                channelType, sinkType);
        MemoryConfigurationProvider provider =
                new MemoryConfigurationProvider(agentName, properties);
        MaterializedConfiguration config = provider.getConfiguration();
        assertEquals(config.getSourceRunners().size(), 0);
        assertEquals(config.getChannels().size(), 1);
        assertEquals(config.getSinkRunners().size(), 1);
    }

}