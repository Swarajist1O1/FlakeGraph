class DummyClass_38633 {
    @Test
    public void testSinkSourceMismatchDuringConfiguration() throws Exception {
        String agentName = "agent1";
        String sourceType = "seq";
        String channelType = "memory";
        String sinkType = "avro";
        Map<String, String> properties = getProperties(agentName, sourceType,
                channelType, sinkType);
        properties.put(agentName + ".channels.channel1.capacity", "1000");
        properties.put(agentName + ".channels.channel1.transactionCapacity", "1000");
        properties.put(agentName + ".sources.source1.batchSize", "1000");
        properties.put(agentName + ".sinks.sink1.batch-size", "1000");
        properties.put(agentName + ".sinks.sink1.hostname", "10.10.10.10");
        properties.put(agentName + ".sinks.sink1.port", "1010");

        MemoryConfigurationProvider provider =
                new MemoryConfigurationProvider(agentName, properties);
        MaterializedConfiguration config = provider.getConfiguration();
        assertEquals(config.getSourceRunners().size(), 1);
        assertEquals(config.getChannels().size(), 1);
        assertEquals(config.getSinkRunners().size(), 1);

        properties.put(agentName + ".sources.source1.batchSize", "1001");
        properties.put(agentName + ".sinks.sink1.batch-size", "1000");

        provider = new MemoryConfigurationProvider(agentName, properties);
        config = provider.getConfiguration();
        assertEquals(config.getSourceRunners().size(), 0);
        assertEquals(config.getChannels().size(), 1);
        assertEquals(config.getSinkRunners().size(), 1);

        properties.put(agentName + ".sources.source1.batchSize", "1000");
        properties.put(agentName + ".sinks.sink1.batch-size", "1001");

        provider = new MemoryConfigurationProvider(agentName, properties);
        config = provider.getConfiguration();
        assertEquals(config.getSourceRunners().size(), 1);
        assertEquals(config.getChannels().size(), 1);
        assertEquals(config.getSinkRunners().size(), 0);

        properties.put(agentName + ".sources.source1.batchSize", "1001");
        properties.put(agentName + ".sinks.sink1.batch-size", "1001");

        provider = new MemoryConfigurationProvider(agentName, properties);
        config = provider.getConfiguration();
        assertEquals(config.getSourceRunners().size(), 0);
        assertEquals(config.getChannels().size(), 0);
        assertEquals(config.getSinkRunners().size(), 0);
    }

}