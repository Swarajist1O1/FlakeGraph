class DummyClass_38625 {
    @Test
    public void testDispoableChannel() throws Exception {
        String agentName = "agent1";
        Map<String, String> properties = getPropertiesForChannel(agentName,
                DisposableChannel.class.getName());
        MemoryConfigurationProvider provider =
                new MemoryConfigurationProvider(agentName, properties);
        MaterializedConfiguration config1 = provider.getConfiguration();
        Channel channel1 = config1.getChannels().values().iterator().next();
        assertTrue(channel1 instanceof DisposableChannel);
        MaterializedConfiguration config2 = provider.getConfiguration();
        Channel channel2 = config2.getChannels().values().iterator().next();
        assertTrue(channel2 instanceof DisposableChannel);
        assertNotSame(channel1, channel2);
    }

}