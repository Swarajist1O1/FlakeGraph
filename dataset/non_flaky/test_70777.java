class DummyClass_70777 {
    @Test
    public void ensureInternalEndpointIsSecured() throws Throwable {
        final String connectorTasksEndpoint = connect.endpointForResource(String.format(
            "connectors/%s/tasks",
            CONNECTOR_NAME
        ));
        final Map<String, String> emptyHeaders = new HashMap<>();
        final Map<String, String> invalidSignatureHeaders = new HashMap<>();
        invalidSignatureHeaders.put(SIGNATURE_HEADER, "S2Fma2Flc3F1ZQ==");
        invalidSignatureHeaders.put(SIGNATURE_ALGORITHM_HEADER, "HmacSHA256");

        // We haven't created the connector yet, but this should still return a 400 instead of a 404
        // if the endpoint is secured
        log.info(
            "Making a POST request to the {} endpoint with no connector started and no signature header; " 
                + "expecting 400 error response",
            connectorTasksEndpoint
        );
        assertEquals(
            BAD_REQUEST.getStatusCode(),
            connect.executePost(connectorTasksEndpoint, "[]", emptyHeaders)
        );

        // Try again, but with an invalid signature
        log.info(
            "Making a POST request to the {} endpoint with no connector started and an invalid signature header; "
                + "expecting 403 error response",
            connectorTasksEndpoint
        );
        assertEquals(
            FORBIDDEN.getStatusCode(),
            connect.executePost(connectorTasksEndpoint, "[]", invalidSignatureHeaders)
        );

        // Create the connector now
        // setup up props for the sink connector
        Map<String, String> connectorProps = new HashMap<>();
        connectorProps.put(CONNECTOR_CLASS_CONFIG, MonitorableSinkConnector.class.getSimpleName());
        connectorProps.put(TASKS_MAX_CONFIG, String.valueOf(1));
        connectorProps.put(TOPICS_CONFIG, "test-topic");
        connectorProps.put(KEY_CONVERTER_CLASS_CONFIG, StringConverter.class.getName());
        connectorProps.put(VALUE_CONVERTER_CLASS_CONFIG, StringConverter.class.getName());

        // start a sink connector
        log.info("Starting the {} connector", CONNECTOR_NAME);
        StartAndStopLatch startLatch = connectorHandle.expectedStarts(1);
        connect.configureConnector(CONNECTOR_NAME, connectorProps);
        startLatch.await(CONNECTOR_SETUP_DURATION_MS, TimeUnit.MILLISECONDS);


        // Verify the exact same behavior, after starting the connector

        // We haven't created the connector yet, but this should still return a 400 instead of a 404
        // if the endpoint is secured
        log.info(
            "Making a POST request to the {} endpoint with the connector started and no signature header; "
                + "expecting 400 error response",
            connectorTasksEndpoint
        );
        assertEquals(
            BAD_REQUEST.getStatusCode(),
            connect.executePost(connectorTasksEndpoint, "[]", emptyHeaders)
        );

        // Try again, but with an invalid signature
        log.info(
            "Making a POST request to the {} endpoint with the connector started and an invalid signature header; "
                + "expecting 403 error response",
            connectorTasksEndpoint
        );
        assertEquals(
            FORBIDDEN.getStatusCode(),
            connect.executePost(connectorTasksEndpoint, "[]", invalidSignatureHeaders)
        );
    }

}