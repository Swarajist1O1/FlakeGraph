class DummyClass_70773 {
    @Test
    public void testReconfigConnector() throws Exception {
        ConnectorHandle connectorHandle = RuntimeHandles.get().connectorHandle(CONNECTOR_NAME);

        // create test topic
        String anotherTopic = "another-topic";
        connect.kafka().createTopic(TOPIC_NAME, NUM_TOPIC_PARTITIONS);
        connect.kafka().createTopic(anotherTopic, NUM_TOPIC_PARTITIONS);

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

        int numRecordsProduced = 100;
        long recordTransferDurationMs = TimeUnit.SECONDS.toMillis(30);

        // consume all records from the source topic or fail, to ensure that they were correctly produced
        int recordNum = connect.kafka().consume(numRecordsProduced, recordTransferDurationMs, TOPIC_NAME).count();
        assertTrue("Not enough records produced by source connector. Expected at least: " + numRecordsProduced + " + but got " + recordNum,
                recordNum >= numRecordsProduced);

        // expect that we're going to restart the connector and its tasks
        StartAndStopLatch restartLatch = connectorHandle.expectedStarts(1);

        // Reconfigure the source connector by changing the Kafka topic used as output
        props.put(TOPIC_CONFIG, anotherTopic);
        connect.configureConnector(CONNECTOR_NAME, props);

        // Wait for the connector *and tasks* to be restarted
        assertTrue("Failed to alter connector configuration and see connector and tasks restart "
                   + "within " + CONNECTOR_SETUP_DURATION_MS + "ms",
                restartLatch.await(CONNECTOR_SETUP_DURATION_MS, TimeUnit.MILLISECONDS));

        // And wait for the Connect to show the connectors and tasks are running
        waitForCondition(() -> this.assertConnectorAndTasksRunning(CONNECTOR_NAME, NUM_TASKS).orElse(false),
                CONNECTOR_SETUP_DURATION_MS, "Connector tasks did not start in time.");

        // consume all records from the source topic or fail, to ensure that they were correctly produced
        recordNum = connect.kafka().consume(numRecordsProduced, recordTransferDurationMs, anotherTopic).count();
        assertTrue("Not enough records produced by source connector. Expected at least: " + numRecordsProduced + " + but got " + recordNum,
                recordNum >= numRecordsProduced);
    }

}