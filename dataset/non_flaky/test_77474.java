class DummyClass_77474 {
    @TestLogging(reason="testing that DEBUG-level logging is reasonable", value="org.opensearch.cluster.NodeConnectionsService:DEBUG")
    public void testDebugLogging() throws IllegalAccessException {
        final DeterministicTaskQueue deterministicTaskQueue
            = new DeterministicTaskQueue(builder().put(NODE_NAME_SETTING.getKey(), "node").build(), random());

        MockTransport transport = new MockTransport(deterministicTaskQueue.getThreadPool());
        TestTransportService transportService = new TestTransportService(transport, deterministicTaskQueue.getThreadPool());
        transportService.start();
        transportService.acceptIncomingRequests();

        final NodeConnectionsService service
            = new NodeConnectionsService(Settings.EMPTY, deterministicTaskQueue.getThreadPool(), transportService);
        service.start();

        final List<DiscoveryNode> allNodes = generateNodes();
        final DiscoveryNodes targetNodes = discoveryNodesFromList(randomSubsetOf(allNodes));
        service.connectToNodes(targetNodes, () -> {});
        deterministicTaskQueue.runAllRunnableTasks();

        // periodic reconnections to unexpectedly-disconnected nodes are logged
        final Set<DiscoveryNode> disconnectedNodes = new HashSet<>(randomSubsetOf(allNodes));
        for (DiscoveryNode disconnectedNode : disconnectedNodes) {
            transportService.disconnectFromNode(disconnectedNode);
        }
        MockLogAppender appender = new MockLogAppender();
        try {
            appender.start();
            Loggers.addAppender(LogManager.getLogger("org.opensearch.cluster.NodeConnectionsService"), appender);
            for (DiscoveryNode targetNode : targetNodes) {
                if (disconnectedNodes.contains(targetNode)) {
                    appender.addExpectation(new MockLogAppender.SeenEventExpectation("connecting to " + targetNode,
                        "org.opensearch.cluster.NodeConnectionsService", Level.DEBUG,
                        "connecting to " + targetNode));
                    appender.addExpectation(new MockLogAppender.SeenEventExpectation("connected to " + targetNode,
                        "org.opensearch.cluster.NodeConnectionsService", Level.DEBUG,
                        "connected to " + targetNode));
                } else {
                    appender.addExpectation(new MockLogAppender.UnseenEventExpectation("connecting to " + targetNode,
                        "org.opensearch.cluster.NodeConnectionsService", Level.DEBUG,
                        "connecting to " + targetNode));
                    appender.addExpectation(new MockLogAppender.UnseenEventExpectation("connected to " + targetNode,
                        "org.opensearch.cluster.NodeConnectionsService", Level.DEBUG,
                        "connected to " + targetNode));
                }
            }

            runTasksUntil(deterministicTaskQueue, CLUSTER_NODE_RECONNECT_INTERVAL_SETTING.get(Settings.EMPTY).millis());
            appender.assertAllExpectationsMatched();
        } finally {
            Loggers.removeAppender(LogManager.getLogger("org.opensearch.cluster.NodeConnectionsService"), appender);
            appender.stop();
        }        for (DiscoveryNode disconnectedNode : disconnectedNodes) {
            transportService.disconnectFromNode(disconnectedNode);
        }

        // changes to the expected set of nodes are logged, including reconnections to any unexpectedly-disconnected nodes
        final DiscoveryNodes newTargetNodes = discoveryNodesFromList(randomSubsetOf(allNodes));
        for (DiscoveryNode disconnectedNode : disconnectedNodes) {
            transportService.disconnectFromNode(disconnectedNode);
        }
        appender = new MockLogAppender();
        try {
            appender.start();
            Loggers.addAppender(LogManager.getLogger("org.opensearch.cluster.NodeConnectionsService"), appender);
            for (DiscoveryNode targetNode : targetNodes) {
                if (disconnectedNodes.contains(targetNode) && newTargetNodes.get(targetNode.getId()) != null) {
                    appender.addExpectation(new MockLogAppender.SeenEventExpectation("connecting to " + targetNode,
                        "org.opensearch.cluster.NodeConnectionsService", Level.DEBUG,
                        "connecting to " + targetNode));
                    appender.addExpectation(new MockLogAppender.SeenEventExpectation("connected to " + targetNode,
                        "org.opensearch.cluster.NodeConnectionsService", Level.DEBUG,
                        "connected to " + targetNode));
                } else {
                    appender.addExpectation(new MockLogAppender.UnseenEventExpectation("connecting to " + targetNode,
                        "org.opensearch.cluster.NodeConnectionsService", Level.DEBUG,
                        "connecting to " + targetNode));
                    appender.addExpectation(new MockLogAppender.UnseenEventExpectation("connected to " + targetNode,
                        "org.opensearch.cluster.NodeConnectionsService", Level.DEBUG,
                        "connected to " + targetNode));
                }
                if (newTargetNodes.get(targetNode.getId()) == null) {
                    appender.addExpectation(new MockLogAppender.SeenEventExpectation("disconnected from " + targetNode,
                        "org.opensearch.cluster.NodeConnectionsService", Level.DEBUG,
                        "disconnected from " + targetNode));
                }
            }
            for (DiscoveryNode targetNode : newTargetNodes) {
                appender.addExpectation(new MockLogAppender.UnseenEventExpectation("disconnected from " + targetNode,
                    "org.opensearch.cluster.NodeConnectionsService", Level.DEBUG,
                    "disconnected from " + targetNode));
                if (targetNodes.get(targetNode.getId()) == null) {
                    appender.addExpectation(new MockLogAppender.SeenEventExpectation("connecting to " + targetNode,
                        "org.opensearch.cluster.NodeConnectionsService", Level.DEBUG,
                        "connecting to " + targetNode));
                    appender.addExpectation(new MockLogAppender.SeenEventExpectation("connected to " + targetNode,
                        "org.opensearch.cluster.NodeConnectionsService", Level.DEBUG,
                        "connected to " + targetNode));
                }
            }

            service.disconnectFromNodesExcept(newTargetNodes);
            service.connectToNodes(newTargetNodes, () -> {});
            deterministicTaskQueue.runAllRunnableTasks();
            appender.assertAllExpectationsMatched();
        } finally {
            Loggers.removeAppender(LogManager.getLogger("org.opensearch.cluster.NodeConnectionsService"), appender);
            appender.stop();
        }
    }

}