class DummyClass_134004 {
    @Test(timeout = 300000)
    public void nodesPausedAndPartitionedTest() {
        workflow(wf -> {
            wf.deploy();

            CorfuCluster corfuCluster = wf.getUniverse()
                    .getGroup(wf.getFixture().data().getGroupParamByIndex(0).getName());

            CorfuClient corfuClient = corfuCluster.getLocalCorfuClient();

            CorfuTable<String, String> table = corfuClient
                    .createDefaultCorfuTable(DEFAULT_STREAM_NAME);

            for (int i = 0; i < TestFixtureConst.DEFAULT_TABLE_ITER; i++) {
                table.put(String.valueOf(i), String.valueOf(i));
            }

            //Should pause one node and partition another
            CorfuServer server0 = corfuCluster.getServerByIndex(0);
            CorfuServer server1 = corfuCluster.getServerByIndex(1);
            CorfuServer server2 = corfuCluster.getServerByIndex(2);

            // Pause one node and partition another one
            server1.pause();
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

            // Recover cluster by resuming the paused node, removing
            // partition and wait for layout's unresponsive servers to change.
            // Also wait for the segment merge.
            server1.resume();
            server2.reconnect(Arrays.asList(server0, server1));
            waitForUnresponsiveServersChange(size -> size == 0, corfuClient);
            waitForLayoutChange(layout -> layout.getSegments().size() == 1, corfuClient);
            // Verify cluster status is STABLE
            corfuClient.invalidateLayout();
            clusterStatusReport = corfuClient.getManagementView().getClusterStatus();
            assertThat(clusterStatusReport.getClusterStatus()).isEqualTo(ClusterStatus.STABLE);

            // Verify data path working fine
            for (int i = 0; i < TestFixtureConst.DEFAULT_TABLE_ITER; i++) {
                assertThat(table.get(String.valueOf(i))).isEqualTo(String.valueOf(i));
            }

            corfuClient.shutdown();
        });
    }

}