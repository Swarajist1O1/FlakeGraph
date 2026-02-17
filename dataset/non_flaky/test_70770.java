class DummyClass_70770 {
    @Test
    public void testRestartFailedTask() throws Exception {
        connect = connectBuilder.build();
        // start the clusters
        connect.start();

        int numTasks = 1;

        // Properties for the source connector. The task should fail at startup due to the bad broker address.
        Map<String, String> connectorProps = new HashMap<>();
        connectorProps.put(CONNECTOR_CLASS_CONFIG, MonitorableSourceConnector.class.getName());
        connectorProps.put(TASKS_MAX_CONFIG, Objects.toString(numTasks));
        connectorProps.put(CONNECTOR_CLIENT_PRODUCER_OVERRIDES_PREFIX + BOOTSTRAP_SERVERS_CONFIG, "nobrokerrunningatthisaddress");

        waitForCondition(() -> assertWorkersUp(NUM_WORKERS).orElse(false),
                WORKER_SETUP_DURATION_MS, "Initial group of workers did not start in time.");

        // Try to start the connector and its single task.
        connect.configureConnector(CONNECTOR_NAME, connectorProps);

        waitForCondition(() -> assertConnectorTasksFailed(CONNECTOR_NAME, numTasks).orElse(false),
                CONNECTOR_SETUP_DURATION_MS, "Connector tasks did not fail in time");

        // Reconfigure the connector without the bad broker address.
        connectorProps.remove(CONNECTOR_CLIENT_PRODUCER_OVERRIDES_PREFIX + BOOTSTRAP_SERVERS_CONFIG);
        connect.configureConnector(CONNECTOR_NAME, connectorProps);

        // Restart the failed task
        String taskRestartEndpoint = connect.endpointForResource(
            String.format("connectors/%s/tasks/0/restart", CONNECTOR_NAME));
        connect.executePost(taskRestartEndpoint, "", Collections.emptyMap());

        // Ensure the task started successfully this time
        waitForCondition(() -> assertConnectorAndTasksRunning(CONNECTOR_NAME, numTasks).orElse(false),
            CONNECTOR_SETUP_DURATION_MS, "Connector tasks are not all in running state.");
    }

}