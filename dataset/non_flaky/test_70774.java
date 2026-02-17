class DummyClass_70774 {
    @Test
    public void testDeleteConnector() throws Exception {
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

        waitForCondition(() -> this.assertWorkersUp(3),
                WORKER_SETUP_DURATION_MS, "Connect workers did not start in time.");

        // start a source connector
        IntStream.range(0, 4).forEachOrdered(
            i -> {
                try {
                    connect.configureConnector(CONNECTOR_NAME + i, props);
                } catch (IOException e) {
                    throw new ConnectException(e);
                }
            });

        waitForCondition(() -> this.assertConnectorAndTasksRunning(CONNECTOR_NAME + 3, NUM_TASKS).orElse(true),
                CONNECTOR_SETUP_DURATION_MS, "Connector tasks did not start in time.");

        // delete connector
        connect.deleteConnector(CONNECTOR_NAME + 3);

        waitForCondition(() -> !this.assertConnectorAndTasksRunning(CONNECTOR_NAME + 3, NUM_TASKS).orElse(true),
                CONNECTOR_SETUP_DURATION_MS, "Connector tasks did not stop in time.");

        waitForCondition(this::assertConnectorAndTasksAreUnique,
                WORKER_SETUP_DURATION_MS, "Connect and tasks are imbalanced between the workers.");
    }

}