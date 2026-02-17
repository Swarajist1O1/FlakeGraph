class DummyClass_70772 {
    @Test
    public void testStartTwoConnectors() throws Exception {
        // create test topic
        connect.kafka().createTopic(TOPIC_NAME, NUM_TOPIC_PARTITIONS);

        // setup up props for the source connector
        Map<String, String> props = new HashMap<>();
        props.put(CONNECTOR_CLASS_CONFIG, MonitorableSourceConnector.class.getSimpleName());
        props.put(TASKS_MAX_CONFIG, String.valueOf(NUM_TASKS));
        props.put("throughput", String.valueOf(1));
        props.put("messages.per.poll", String.valueOf(10));
        props.put(TOPIC_CONFIG, TOPIC_NAME);
        props.put(KEY_CONVERTER_CLASS_CONFIG, StringConverter.class.getName());
        props.put(VALUE_CONVERTER_CLASS_CONFIG, StringConverter.class.getName());

        // start a source connector
        connect.configureConnector(CONNECTOR_NAME, props);

        waitForCondition(() -> this.assertConnectorAndTasksRunning(CONNECTOR_NAME, NUM_TASKS).orElse(false),
                CONNECTOR_SETUP_DURATION_MS, "Connector tasks did not start in time.");

        // start a source connector
        connect.configureConnector("another-source", props);

        waitForCondition(() -> this.assertConnectorAndTasksRunning(CONNECTOR_NAME, NUM_TASKS).orElse(false),
                CONNECTOR_SETUP_DURATION_MS, "Connector tasks did not start in time.");

        waitForCondition(() -> this.assertConnectorAndTasksRunning("another-source", 4).orElse(false),
                CONNECTOR_SETUP_DURATION_MS, "Connector tasks did not start in time.");
    }

}