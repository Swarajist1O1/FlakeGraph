class DummyClass_91466 {
    @TestLogging("_root:DEBUG,org.elasticsearch.cluster.service:TRACE,org.elasticsearch.test.disruption:TRACE")
    public void testStaleMasterNotHijackingMajority() throws Exception {
        // 3 node cluster with unicast discovery and minimum_master_nodes set to 2:
        final List<String> nodes = startCluster(3, 2);

        // Save the current master node as old master node, because that node will get frozen
        final String oldMasterNode = internalCluster().getMasterName();
        for (String node : nodes) {
            ensureStableCluster(3, node);
        }
        assertMaster(oldMasterNode, nodes);

        // Simulating a painful gc by suspending all threads for a long time on the current elected master node.
        SingleNodeDisruption masterNodeDisruption = new LongGCDisruption(random(), oldMasterNode);

        // Save the majority side
        final List<String> majoritySide = new ArrayList<>(nodes);
        majoritySide.remove(oldMasterNode);

        // Keeps track of the previous and current master when a master node transition took place on each node on the majority side:
        final Map<String, List<Tuple<String, String>>> masters = Collections.synchronizedMap(new HashMap<String, List<Tuple<String,
                        String>>>());
        for (final String node : majoritySide) {
            masters.put(node, new ArrayList<Tuple<String, String>>());
            internalCluster().getInstance(ClusterService.class, node).addListener(event -> {
                DiscoveryNode previousMaster = event.previousState().nodes().getMasterNode();
                DiscoveryNode currentMaster = event.state().nodes().getMasterNode();
                if (!Objects.equals(previousMaster, currentMaster)) {
                    logger.info("node {} received new cluster state: {} \n and had previous cluster state: {}", node, event.state(),
                            event.previousState());
                    String previousMasterNodeName = previousMaster != null ? previousMaster.getName() : null;
                    String currentMasterNodeName = currentMaster != null ? currentMaster.getName() : null;
                    masters.get(node).add(new Tuple<>(previousMasterNodeName, currentMasterNodeName));
                }
            });
        }

        final CountDownLatch oldMasterNodeSteppedDown = new CountDownLatch(1);
        internalCluster().getInstance(ClusterService.class, oldMasterNode).addListener(event -> {
            if (event.state().nodes().getMasterNodeId() == null) {
                oldMasterNodeSteppedDown.countDown();
            }
        });

        internalCluster().setDisruptionScheme(masterNodeDisruption);
        logger.info("freezing node [{}]", oldMasterNode);
        masterNodeDisruption.startDisrupting();

        // Wait for the majority side to get stable
        assertDifferentMaster(majoritySide.get(0), oldMasterNode);
        assertDifferentMaster(majoritySide.get(1), oldMasterNode);

        // the test is periodically tripping on the following assertion. To find out which threads are blocking the nodes from making
        // progress we print a stack dump
        boolean failed = true;
        try {
            assertDiscoveryCompleted(majoritySide);
            failed = false;
        } finally {
            if (failed) {
                logger.error("discovery failed to complete, probably caused by a blocked thread: {}",
                        new HotThreads().busiestThreads(Integer.MAX_VALUE).ignoreIdleThreads(false).detect());
            }
        }

        // The old master node is frozen, but here we submit a cluster state update task that doesn't get executed,
        // but will be queued and once the old master node un-freezes it gets executed.
        // The old master node will send this update + the cluster state where he is flagged as master to the other
        // nodes that follow the new master. These nodes should ignore this update.
        internalCluster().getInstance(ClusterService.class, oldMasterNode).submitStateUpdateTask("sneaky-update", new
                ClusterStateUpdateTask(Priority.IMMEDIATE) {
                    @Override
                    public ClusterState execute(ClusterState currentState) throws Exception {
                        return ClusterState.builder(currentState).build();
                    }

}