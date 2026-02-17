class DummyClass_38643 {
    @Test
    public void testPropertyRead() {

        FlumeConfiguration configuration = provider.getFlumeConfiguration();
        assertNotNull(configuration);

    /*
     * Test the known errors in the file
     */
        List<String> expected = Lists.newArrayList();
        expected.add("host5 CONFIG_ERROR");
        expected.add("host5 INVALID_PROPERTY");
        expected.add("host4 CONFIG_ERROR");
        expected.add("host4 CONFIG_ERROR");
        expected.add("host4 PROPERTY_VALUE_NULL");
        expected.add("host4 PROPERTY_VALUE_NULL");
        expected.add("host4 PROPERTY_VALUE_NULL");
        expected.add("host4 AGENT_CONFIGURATION_INVALID");
        expected.add("ch2 ATTRS_MISSING");
        expected.add("host3 CONFIG_ERROR");
        expected.add("host3 PROPERTY_VALUE_NULL");
        expected.add("host3 AGENT_CONFIGURATION_INVALID");
        expected.add("host2 PROPERTY_VALUE_NULL");
        expected.add("host2 AGENT_CONFIGURATION_INVALID");
        List<String> actual = Lists.newArrayList();
        for (FlumeConfigurationError error : configuration.getConfigurationErrors()) {
            actual.add(error.getComponentName() + " " + error.getErrorType().toString());
        }
        Collections.sort(expected);
        Collections.sort(actual);
        assertEquals(actual, expected);

        AgentConfiguration agentConfiguration =
                configuration.getConfigurationFor("host1");
        assertNotNull(agentConfiguration);

        LOGGER.info(agentConfiguration.getPrevalidationConfig());
        LOGGER.info(agentConfiguration.getPostvalidationConfig());

        Set<String> sources = Sets.newHashSet("source1");
        Set<String> sinks = Sets.newHashSet("sink1");
        Set<String> channels = Sets.newHashSet("channel1");

        assertEquals(agentConfiguration.getSourceSet(), sources);
        assertEquals(agentConfiguration.getSinkSet(), sinks);
        assertEquals(agentConfiguration.getChannelSet(), channels);
    }

}