class DummyClass_70769 {
    @Test
    public void testAddAndRemoveWorker() throws Exception {
        connect = connectBuilder.build();
        // start the clusters
        connect.start();

        int numTasks = 4;
        // create test topic
        connect.kafka().createTopic("test-topic", NUM_TOPIC_PARTITIONS);

        // setup up props for the sink connector
        Map<String, String> props = new HashMap<>();
        props.put(CONNECTOR_CLASS_CONFIG, MonitorableSourceConnector.class.getSimpleName());
        props.put(TASKS_MAX_CONFIG, String.valueOf(numTasks));
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

        WorkerHandle extraWorker = connect.addWorker();

        waitForCondition(() -> assertWorkersUp(NUM_WORKERS + 1).orElse(false),
                WORKER_SETUP_DURATION_MS, "Expanded group of workers did not start in time.");

        waitForCondition(() -> assertConnectorAndTasksRunning(CONNECTOR_NAME, numTasks).orElse(false),
                CONNECTOR_SETUP_DURATION_MS, "Connector tasks are not all in running state.");

        Set<WorkerHandle> workers = connect.activeWorkers();
        assertTrue(workers.contains(extraWorker));

        connect.removeWorker(extraWorker);

        waitForCondition(() -> assertWorkersUp(NUM_WORKERS).orElse(false) && !assertWorkersUp(NUM_WORKERS + 1).orElse(false),
                WORKER_SETUP_DURATION_MS, "Group of workers did not shrink in time.");

        workers = connect.activeWorkers();
        assertFalse(workers.contains(extraWorker));
    }

}