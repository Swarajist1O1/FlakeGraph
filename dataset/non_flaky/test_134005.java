class DummyClass_134005 {
    @Test(timeout = 600000)
    public void concurrentClusterResizeTest() {
        // Deploy a five nodes cluster
        final int numNodes = 5;

        workflow(wf -> {
            wf.setupDocker(fixture -> fixture.getCluster().numNodes(numNodes));
            wf.setupProcess(fixture -> fixture.getCluster().numNodes(numNodes));
            wf.setupVm(fixture -> fixture.getCluster().numNodes(numNodes));

            wf.deploy();

            ClientParams clientFixture = ClientParams.builder().build();
            CorfuCluster corfuCluster = wf.getUniverse()
                    .getGroup(wf.getFixture().data().getGroupParamByIndex(0).getName());

            assertThat(corfuCluster.nodes().size()).isEqualTo(numNodes);

            CorfuClient corfuClient = corfuCluster.getLocalCorfuClient();

            CorfuTable<String, String> table =
                    corfuClient.createDefaultCorfuTable(TestFixtureConst.DEFAULT_STREAM_NAME);
            for (int i = 0; i < TestFixtureConst.DEFAULT_TABLE_ITER; i++) {
                table.put(String.valueOf(i), String.valueOf(i));
            }

            CorfuServer server0 = corfuCluster.getFirstServer();

            // Get the servers list to be added/removed -all servers in the cluster exclude server0
            List<CorfuServer> servers = IntStream.range(1, numNodes)
                    .mapToObj(corfuCluster::getServerByIndex)
                    .collect(Collectors.toList());

            //should concurrently remove four nodes from cluster

            // Concurrently remove four nodes from cluster
            ExecutorService executor = Executors.newFixedThreadPool(numNodes - 1);

            servers.forEach(node -> {
                Runnable removeNodeAction = () -> corfuClient.getManagementView().removeNode(
                        node.getEndpoint(),
                        clientFixture.getNumRetry(),
                        clientFixture.getTimeout(),
                        clientFixture.getPollPeriod()
                );
                executor.submit(removeNodeAction);
            });

            // Wait for layout servers to change and wait for cluster to be up
            waitForLayoutServersChange(size -> size == 1, corfuClient);
            executor.shutdownNow();

            // Verify layout contains only one node
            corfuClient.invalidateLayout();
            assertThat(corfuClient.getLayout().getAllServers()).containsExactly(server0.getEndpoint());

            waitForClusterUp(table, "0");
            // Verify data path working fine
            for (int x = 0; x < TestFixtureConst.DEFAULT_TABLE_ITER; x++) {
                assertThat(table.get(String.valueOf(x))).isEqualTo(String.valueOf(x));
            }

            //should concurrently add four nodes back into cluster"

            // Concurrently add four nodes back into cluster and wait for cluster to stabilize
            ExecutorService executor2 = Executors.newFixedThreadPool(numNodes - 1);
            servers.forEach(node -> executor2.submit(() -> corfuClient.getManagementView().addNode(
                    node.getEndpoint(),
                    clientFixture.getNumRetry(),
                    clientFixture.getTimeout(),
                    clientFixture.getPollPeriod())
            ));


            // Check that the segments are merged and all the servers are equal to numNodes
            waitForLayoutChange(layout -> layout.getAllServers().size() == numNodes, corfuClient);
            waitForLayoutChange(layout -> layout.getSegments().size() == 1, corfuClient);
            // wait for the cluster to be up
            waitForClusterUp(table, "0");
            executor2.shutdownNow();

            // Verify data path working fine
            for (int x = 0; x < TestFixtureConst.DEFAULT_TABLE_ITER; x++) {
                assertThat(table.get(String.valueOf(x))).isEqualTo(String.valueOf(x));
            }

            corfuClient.shutdown();
        });
    }

}