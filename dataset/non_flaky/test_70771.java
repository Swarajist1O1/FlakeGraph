class DummyClass_70771 {
    @Test
    public void testBrokerCoordinator() throws Exception {
        workerProps.put(DistributedConfig.SCHEDULED_REBALANCE_MAX_DELAY_MS_CONFIG, String.valueOf(5000));
        connect = connectBuilder.workerProps(workerProps).build();
        // start the clusters
        connect.start();
        int numTasks = 4;
        // create test topic
        connect.kafka().createTopic("test-topic", NUM_TOPIC_PARTITIONS);

        // setup up props for the sink connector
        Map<String, String> props = new HashMap<>();
        props.put(CONNECTOR_CLASS_CONFIG, MonitorableSourceConnector.class.getSimpleName());
        props.put(TASKS_MAX_CONFIG, String.valueOf(numTasks));
        props.put("topic", "test-topic");
        props.put("throughput", String.valueOf(1));
        props.put("messages.per.poll", String.valueOf(10));
        props.put(KEY_CONVERTER_CLASS_CONFIG, StringConverter.class.getName());
        props.put(VALUE_CONVERTER_CLASS_CONFIG, StringConverter.class.getName());

        waitForCondition(() -> assertWorkersUp(NUM_WORKERS).orElse(false),
                WORKER_SETUP_DURATION_MS, "Initial group of workers did not start in time.");

        // start a source connector
        connect.configureConnector(CONNECTOR_NAME, props);

        waitForCondition(() -> assertConnectorAndTasksRunning(CONNECTOR_NAME, numTasks).orElse(false),
                CONNECTOR_SETUP_DURATION_MS, "Connector tasks did not start in time.");

        connect.kafka().stopOnlyKafka();

        waitForCondition(() -> assertWorkersUp(NUM_WORKERS).orElse(false),
                WORKER_SETUP_DURATION_MS, "Group of workers did not remain the same after broker shutdown");

        // Allow for the workers to discover that the coordinator is unavailable, wait is
        // heartbeat timeout * 2 + 4sec
        Thread.sleep(TimeUnit.SECONDS.toMillis(10));

        connect.kafka().startOnlyKafkaOnSamePorts();

        // Allow for the kafka brokers to come back online
        Thread.sleep(TimeUnit.SECONDS.toMillis(10));

        waitForCondition(() -> assertWorkersUp(NUM_WORKERS).orElse(false),
                WORKER_SETUP_DURATION_MS, "Group of workers did not remain the same within the "
                        + "designated time.");

        // Allow for the workers to rebalance and reach a steady state
        Thread.sleep(TimeUnit.SECONDS.toMillis(10));

        waitForCondition(() -> assertConnectorAndTasksRunning(CONNECTOR_NAME, numTasks).orElse(false),
                CONNECTOR_SETUP_DURATION_MS, "Connector tasks did not start in time.");
    }

}