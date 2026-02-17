class DummyClass_133997 {
    @Test(timeout = 300000)
    public void nodeDownAndPartitionTest() {
        workflow(wf -> {
            wf.deploy();

            CorfuCluster corfuCluster = wf.getUniverse()
                    .getGroup(wf.getFixture().data().getGroupParamByIndex(0).getName());

            CorfuClient corfuClient = corfuCluster.getLocalCorfuClient();

            CorfuTable<String, String> table = corfuClient
                    .createDefaultCorfuTable(DEFAULT_STREAM_NAME);

            for (int i = 0; i < DEFAULT_TABLE_ITER; i++) {
                table.put(String.valueOf(i), String.valueOf(i));
            }

            //Should stop one node and partition another one"
            CorfuServer server0 = corfuCluster.getServerByIndex(0);
            CorfuServer server1 = corfuCluster.getServerByIndex(1);
            CorfuServer server2 = corfuCluster.getServerByIndex(2);

            // Stop one node and partition another one
            server1.stop(Duration.ofSeconds(10));
            server2.disconnect(Arrays.asList(server0, server1));

            waitUninterruptibly(Duration.ofSeconds(20));

            // Verify cluster status
            corfuClient.invalidateLayout();
            ClusterStatusReport clusterStatusReport = corfuClient
                    .getManagementView()
                    .getClusterStatus();
            assertThat(clusterStatusReport.getClusterStatus()).isEqualTo(ClusterStatus.STABLE);

            // Wait for failure detector finds cluster is down before recovering
            waitForClusterDown(table);

            // Recover cluster by restarting the stopped node, removing
            // partition and wait for layout's unresponsive servers to change
            server1.start();
            server2.reconnect(Arrays.asList(server0, server1));
            waitForUnresponsiveServersChange(size -> size == 0, corfuClient);

            // Check that the segments are merged and all the servers are equal to 3
            waitForLayoutChange(layout -> layout.getSegments().size() == 1 &&
                    layout.getAllServers().size() == 3, corfuClient);
            // wait for the cluster to be up
            waitForClusterUp(table, "0");

            clusterStatusReport = corfuClient.getManagementView().getClusterStatus();
            assertThat(clusterStatusReport.getClusterStatus()).isEqualTo(ClusterStatus.STABLE);

            // Verify data path working fine
            for (int i = 0; i < DEFAULT_TABLE_ITER; i++) {
                assertThat(table.get(String.valueOf(i))).isEqualTo(String.valueOf(i));
            }

            corfuClient.shutdown();
        });
    }

}