class DummyClass_70790 {
    @Test
    public void testRestExtensionApi() throws IOException, InterruptedException {
        // setup Connect worker properties
        Map<String, String> workerProps = new HashMap<>();
        workerProps.put(REST_EXTENSION_CLASSES_CONFIG, IntegrationTestRestExtension.class.getName());

        // build a Connect cluster backed by Kafka and Zk
        connect = new EmbeddedConnectCluster.Builder()
            .name("connect-cluster")
            .numWorkers(1)
            .numBrokers(1)
            .workerProps(workerProps)
            .build();

        // start the clusters
        connect.start();

        WorkerHandle worker = connect.workers().stream()
            .findFirst()
            .orElseThrow(() -> new AssertionError("At least one worker handle should be available"));

        waitForCondition(
            this::extensionIsRegistered,
            REST_EXTENSION_REGISTRATION_TIMEOUT_MS,
            "REST extension was never registered"
        );

        ConnectorHandle connectorHandle = RuntimeHandles.get().connectorHandle("test-conn");
        try {
            // setup up props for the connector
            Map<String, String> connectorProps = new HashMap<>();
            connectorProps.put(CONNECTOR_CLASS_CONFIG, MonitorableSinkConnector.class.getSimpleName());
            connectorProps.put(TASKS_MAX_CONFIG, String.valueOf(1));
            connectorProps.put(TOPICS_CONFIG, "test-topic");

            // start a connector
            connectorHandle.taskHandle(connectorHandle.name() + "-0");
            StartAndStopLatch connectorStartLatch = connectorHandle.expectedStarts(1);
            connect.configureConnector(connectorHandle.name(), connectorProps);
            connectorStartLatch.await(CONNECTOR_HEALTH_AND_CONFIG_TIMEOUT_MS, TimeUnit.MILLISECONDS);

            String workerId = String.format("%s:%d", worker.url().getHost(), worker.url().getPort());
            ConnectorHealth expectedHealth = new ConnectorHealth(
                connectorHandle.name(),
                new ConnectorState(
                    "RUNNING",
                    workerId,
                    null
                ),
                Collections.singletonMap(
                    0,
                    new TaskState(0, "RUNNING", workerId, null)
                ),
                ConnectorType.SINK
            );

            connectorProps.put(NAME_CONFIG, connectorHandle.name());

            // Test the REST extension API; specifically, that the connector's health and configuration
            // are available to the REST extension we registered and that they contain expected values
            waitForCondition(
                () -> verifyConnectorHealthAndConfig(connectorHandle.name(), expectedHealth, connectorProps),
                CONNECTOR_HEALTH_AND_CONFIG_TIMEOUT_MS,
                "Connector health and/or config was never accessible by the REST extension"
            );
        } finally {
            RuntimeHandles.get().deleteConnector(connectorHandle.name());
        }
    }

}