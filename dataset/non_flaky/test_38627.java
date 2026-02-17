class DummyClass_38627 {
    @Test
    public void testUnspecifiedChannel() throws Exception {
        String agentName = "agent1";
        Map<String, String> properties = getPropertiesForChannel(agentName,
                UnspecifiedChannel.class.getName());
        MemoryConfigurationProvider provider =
                new MemoryConfigurationProvider(agentName, properties);

        MaterializedConfiguration config1 = provider.getConfiguration();
        Channel channel1 = config1.getChannels().values().iterator().next();
        assertTrue(channel1 instanceof UnspecifiedChannel);

        MaterializedConfiguration config2 = provider.getConfiguration();
        Channel channel2 = config2.getChannels().values().iterator().next();
        assertTrue(channel2 instanceof UnspecifiedChannel);

        assertSame(channel1, channel2);
    }

}