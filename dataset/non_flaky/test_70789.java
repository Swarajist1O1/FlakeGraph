class DummyClass_70789 {
    @Test
    public void testSourceConnector() throws Exception {
        // create test topic
        connect.kafka().createTopic("test-topic", NUM_TOPIC_PARTITIONS);

        // setup up props for the sink connector
        Map<String, String> props = new HashMap<>();
        props.put(CONNECTOR_CLASS_CONFIG, MonitorableSourceConnector.class.getSimpleName());
        props.put(TASKS_MAX_CONFIG, String.valueOf(NUM_TASKS));
        props.put("topic", "test-topic");
        props.put("throughput", String.valueOf(500));
        props.put(KEY_CONVERTER_CLASS_CONFIG, StringConverter.class.getName());
        props.put(VALUE_CONVERTER_CLASS_CONFIG, StringConverter.class.getName());

        // expect all records to be produced by the connector
        connectorHandle.expectedRecords(NUM_RECORDS_PRODUCED);

        // expect all records to be produced by the connector
        connectorHandle.expectedCommits(NUM_RECORDS_PRODUCED);

        // start a source connector
        connect.configureConnector(CONNECTOR_NAME, props);

        // wait for the connector tasks to produce enough records
        connectorHandle.awaitRecords(RECORD_TRANSFER_DURATION_MS);

        // wait for the connector tasks to commit enough records
        connectorHandle.awaitCommits(RECORD_TRANSFER_DURATION_MS);

        // consume all records from the source topic or fail, to ensure that they were correctly produced
        int recordNum = connect.kafka().consume(NUM_RECORDS_PRODUCED, RECORD_TRANSFER_DURATION_MS, "test-topic").count();
        assertTrue("Not enough records produced by source connector. Expected at least: " + NUM_RECORDS_PRODUCED + " + but got " + recordNum,
                recordNum >= NUM_RECORDS_PRODUCED);

        // delete connector
        connect.deleteConnector(CONNECTOR_NAME);
    }

}