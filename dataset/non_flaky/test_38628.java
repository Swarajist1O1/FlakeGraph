class DummyClass_38628 {
    @Test
    public void testReusableChannelNotReusedLater() throws Exception {
        String agentName = "agent1";
        Map<String, String> propertiesReusable = getPropertiesForChannel(agentName,
                RecyclableChannel.class
                        .getName());
        Map<String, String> propertiesDispoable = getPropertiesForChannel(agentName,
                DisposableChannel.class
                        .getName());
        MemoryConfigurationProvider provider =
                new MemoryConfigurationProvider(agentName, propertiesReusable);
        MaterializedConfiguration config1 = provider.getConfiguration();
        Channel channel1 = config1.getChannels().values().iterator().next();
        assertTrue(channel1 instanceof RecyclableChannel);

        provider.setProperties(propertiesDispoable);
        MaterializedConfiguration config2 = provider.getConfiguration();
        Channel channel2 = config2.getChannels().values().iterator().next();
        assertTrue(channel2 instanceof DisposableChannel);

        provider.setProperties(propertiesReusable);
        MaterializedConfiguration config3 = provider.getConfiguration();
        Channel channel3 = config3.getChannels().values().iterator().next();
        assertTrue(channel3 instanceof RecyclableChannel);

        assertNotSame(channel1, channel3);
    }

}