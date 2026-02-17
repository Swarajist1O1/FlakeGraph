class DummyClass_133996 {
    @Test(timeout = 300000)
    public void handOfGodTest() {
        workflow(wf -> {
            wf.deploy();

            ClientParams clientFixture = ClientParams.builder().build();
            CorfuCluster corfuCluster = wf.getUniverse()
                    .getGroup(wf.getFixture().data().getGroupParamByIndex(0).getName());

            CorfuClient corfuClient = corfuCluster.getLocalCorfuClient();

            CorfuTable<String, String> table = corfuClient.createDefaultCorfuTable(DEFAULT_STREAM_NAME);
            for (int i = 0; i < DEFAULT_TABLE_ITER; i++) {
                table.put(String.valueOf(i), String.valueOf(i));
            }

            //Should force remove two nodes from cluster
            CorfuServer server0 = corfuCluster.getServerByIndex(0);
            CorfuServer server1 = corfuCluster.getServerByIndex(1);
            CorfuServer server2 = corfuCluster.getServerByIndex(2);

            // Sequentially kill two nodes
            server1.kill();
            server2.kill();

            // Force remove the dead nodes
            corfuClient.getManagementView().forceRemoveNode(
                    server1.getEndpoint(),
                    clientFixture.getNumRetry(),
                    clientFixture.getTimeout(),
                    clientFixture.getPollPeriod()
            );

            corfuClient.getManagementView().forceRemoveNode(
                    server2.getEndpoint(),
                    clientFixture.getNumRetry(),
                    clientFixture.getTimeout(),
                    clientFixture.getPollPeriod()
            );

            // Verify layout contains only the node that is up
            corfuClient.invalidateLayout();
            Layout layout = corfuClient.getLayout();
            assertThat(layout.getAllActiveServers()).containsExactly(server0.getEndpoint());

            // Verify cluster status is STABLE
            ClusterStatusReport clusterStatusReport = corfuClient.getManagementView().getClusterStatus();
            assertThat(clusterStatusReport.getClusterStatus()).isEqualTo(ClusterStatus.STABLE);

            ScenarioUtils.waitUninterruptibly(Duration.ofSeconds(30));

            // Verify data path working
            for (int i = 0; i < DEFAULT_TABLE_ITER; i++) {
                assertThat(table.get(String.valueOf(i))).isEqualTo(String.valueOf(i));
            }

            corfuClient.shutdown();
        });
    }

}