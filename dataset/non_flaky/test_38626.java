class DummyClass_38626 {
    @Test
    public void testReusableChannel() throws Exception {
        String agentName = "agent1";
        Map<String, String> properties = getPropertiesForChannel(agentName,
                RecyclableChannel.class.getName());
        MemoryConfigurationProvider provider =
                new MemoryConfigurationProvider(agentName, properties);

        MaterializedConfiguration config1 = provider.getConfiguration();
        Channel channel1 = config1.getChannels().values().iterator().next();
        assertTrue(channel1 instanceof RecyclableChannel);

        MaterializedConfiguration config2 = provider.getConfiguration();
        Channel channel2 = config2.getChannels().values().iterator().next();
        assertTrue(channel2 instanceof RecyclableChannel);

        assertSame(channel1, channel2);
    }

}