class DummyClass_38632 {
    @Test
    public void testSourceAndSinkThrowExceptionDuringConfiguration()
            throws Exception {
        String agentName = "agent1";
        String sourceType = UnconfigurableSource.class.getName();
        String channelType = "memory";
        String sinkType = UnconfigurableSink.class.getName();
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