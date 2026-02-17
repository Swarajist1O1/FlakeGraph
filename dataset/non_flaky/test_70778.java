class DummyClass_70778 {
    @Test
    public void testSkipRetryAndDLQWithHeaders() throws Exception {
        // create test topic
        connect.kafka().createTopic("test-topic");

        // setup connector config
        Map<String, String> props = new HashMap<>();
        props.put(CONNECTOR_CLASS_CONFIG, MonitorableSinkConnector.class.getSimpleName());
        props.put(TASKS_MAX_CONFIG, String.valueOf(NUM_TASKS));
        props.put(TOPICS_CONFIG, "test-topic");
        props.put(KEY_CONVERTER_CLASS_CONFIG, StringConverter.class.getName());
        props.put(VALUE_CONVERTER_CLASS_CONFIG, StringConverter.class.getName());
        props.put(TRANSFORMS_CONFIG, "failing_transform");
        props.put("transforms.failing_transform.type", FaultyPassthrough.class.getName());

        // log all errors, along with message metadata
        props.put(ERRORS_LOG_ENABLE_CONFIG, "true");
        props.put(ERRORS_LOG_INCLUDE_MESSAGES_CONFIG, "true");

        // produce bad messages into dead letter queue
        props.put(DLQ_TOPIC_NAME_CONFIG, DLQ_TOPIC);
        props.put(DLQ_CONTEXT_HEADERS_ENABLE_CONFIG, "true");
        props.put(DLQ_TOPIC_REPLICATION_FACTOR_CONFIG, "1");

        // tolerate all erros
        props.put(ERRORS_TOLERANCE_CONFIG, "all");

        // retry for up to one second
        props.put(ERRORS_RETRY_TIMEOUT_CONFIG, "1000");

        // set expected records to successfully reach the task
        connectorHandle.taskHandle(TASK_ID).expectedRecords(EXPECTED_CORRECT_RECORDS);

        connect.configureConnector(CONNECTOR_NAME, props);

        waitForCondition(this::checkForPartitionAssignment,
                CONNECTOR_SETUP_DURATION_MS,
                "Connector task was not assigned a partition.");

        // produce some strings into test topic
        for (int i = 0; i < NUM_RECORDS_PRODUCED; i++) {
            connect.kafka().produce("test-topic", "key-" + i, "value-" + i);
        }

        // consume all records from test topic
        log.info("Consuming records from test topic");
        int i = 0;
        for (ConsumerRecord<byte[], byte[]> rec : connect.kafka().consume(NUM_RECORDS_PRODUCED, CONSUME_MAX_DURATION_MS, "test-topic")) {
            String k = new String(rec.key());
            String v = new String(rec.value());
            log.debug("Consumed record (key='{}', value='{}') from topic {}", k, v, rec.topic());
            assertEquals("Unexpected key", k, "key-" + i);
            assertEquals("Unexpected value", v, "value-" + i);
            i++;
        }

        // wait for records to reach the task
        connectorHandle.taskHandle(TASK_ID).awaitRecords(CONSUME_MAX_DURATION_MS);

        // consume failed records from dead letter queue topic
        log.info("Consuming records from test topic");
        ConsumerRecords<byte[], byte[]> messages = connect.kafka().consume(EXPECTED_INCORRECT_RECORDS, CONSUME_MAX_DURATION_MS, DLQ_TOPIC);
        for (ConsumerRecord<byte[], byte[]> recs : messages) {
            log.debug("Consumed record (key={}, value={}) from dead letter queue topic {}",
                    new String(recs.key()), new String(recs.value()), DLQ_TOPIC);
            assertTrue(recs.headers().toArray().length > 0);
            assertValue("test-topic", recs.headers(), ERROR_HEADER_ORIG_TOPIC);
            assertValue(RetriableException.class.getName(), recs.headers(), ERROR_HEADER_EXCEPTION);
            assertValue("Error when value='value-7'", recs.headers(), ERROR_HEADER_EXCEPTION_MESSAGE);
        }

        connect.deleteConnector(CONNECTOR_NAME);
    }

}