class DummyClass_70788 {
    @Test
    public void testSinkConnector() throws Exception {
        // create test topic
        connect.kafka().createTopic("test-topic", NUM_TOPIC_PARTITIONS);

        // setup up props for the sink connector
        Map<String, String> props = new HashMap<>();
        props.put(CONNECTOR_CLASS_CONFIG, MonitorableSinkConnector.class.getSimpleName());
        props.put(TASKS_MAX_CONFIG, String.valueOf(NUM_TASKS));
        props.put(TOPICS_CONFIG, "test-topic");
        props.put(KEY_CONVERTER_CLASS_CONFIG, StringConverter.class.getName());
        props.put(VALUE_CONVERTER_CLASS_CONFIG, StringConverter.class.getName());

        // expect all records to be consumed by the connector
        connectorHandle.expectedRecords(NUM_RECORDS_PRODUCED);

        // expect all records to be consumed by the connector
        connectorHandle.expectedCommits(NUM_RECORDS_PRODUCED);

        // start a sink connector
        connect.configureConnector(CONNECTOR_NAME, props);

        waitForCondition(this::checkForPartitionAssignment,
                CONNECTOR_SETUP_DURATION_MS,
                "Connector tasks were not assigned a partition each.");

        // produce some messages into source topic partitions
        for (int i = 0; i < NUM_RECORDS_PRODUCED; i++) {
            connect.kafka().produce("test-topic", i % NUM_TOPIC_PARTITIONS, "key", "simple-message-value-" + i);
        }

        // consume all records from the source topic or fail, to ensure that they were correctly produced.
        assertEquals("Unexpected number of records consumed", NUM_RECORDS_PRODUCED,
                connect.kafka().consume(NUM_RECORDS_PRODUCED, RECORD_TRANSFER_DURATION_MS, "test-topic").count());

        // wait for the connector tasks to consume all records.
        connectorHandle.awaitRecords(RECORD_TRANSFER_DURATION_MS);

        // wait for the connector tasks to commit all records.
        connectorHandle.awaitCommits(RECORD_TRANSFER_DURATION_MS);

        // delete connector
        connect.deleteConnector(CONNECTOR_NAME);
    }

}