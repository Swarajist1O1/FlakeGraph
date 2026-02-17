class DummyClass_134007 {
    @Test(timeout = 300000)
    public void nodeUpAndPartitionedTest() {
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

            //Should fail the node with most link failures to unresponsive set
            // Deploy and bootstrap three nodes
            CorfuServer server0 = corfuCluster.getServerByIndex(0);
            CorfuServer server1 = corfuCluster.getServerByIndex(1);
            CorfuServer server2 = corfuCluster.getServerByIndex(2);

            long currEpoch = corfuClient.getLayout().getEpoch();

            log.info("Stop server1");
            server1.stop(Duration.ofSeconds(10));
            waitForNextEpoch(corfuClient, currEpoch + 1);
            assertThat(corfuClient.getLayout().getUnresponsiveServers())
                    .containsExactly(server1.getEndpoint());
            currEpoch++;

            // Partition the responsive server0 from both unresponsive server1
            // and responsive server2 and reconnect server 1. Wait for layout's unresponsive
            // servers to change After this, cluster becomes unavailable.
            // NOTE: cannot use waitForClusterDown() since the partition only happens on server side,
            // client can still connect to two nodes, write to table,
            // so system down handler will not be triggered.
            server0.disconnect(Arrays.asList(server1, server2));
            server1.start();

            waitForLayoutChange(l -> {
                List<String> unresponsive = l.getUnresponsiveServers();
                return unresponsive.size() == 1 && unresponsive.contains(server0.getEndpoint());
            }, corfuClient);

            // Verify server0 is unresponsive
            List<String> unresponsiveServers = corfuClient.getLayout().getUnresponsiveServers();
            assertThat(unresponsiveServers)
                    .as("Wrong number of unresponsive servers: %s", unresponsiveServers)
                    .containsExactly(server0.getEndpoint());
            currEpoch += 2;

            waitUninterruptibly(Duration.ofSeconds(20));

            // Verify cluster status. Cluster status should be DEGRADED after one node is
            // marked unresponsive
            ClusterStatusReport clusterStatusReport = corfuClient
                    .getManagementView()
                    .getClusterStatus();
            assertThat(clusterStatusReport.getClusterStatus()).isEqualTo(ClusterStatus.DEGRADED);

            // Heal all the link failures
            server0.reconnect(Arrays.asList(server1, server2));
            waitForNextEpoch(corfuClient, currEpoch + 1);
            currEpoch++;

            Duration sleepDuration = Duration.ofSeconds(1);
            // Verify cluster status is STABLE
            clusterStatusReport = corfuClient.getManagementView().getClusterStatus();
            while (!clusterStatusReport.getClusterStatus().equals(ClusterStatus.STABLE)) {
                clusterStatusReport = corfuClient.getManagementView().getClusterStatus();
                Sleep.sleepUninterruptibly(sleepDuration);
            }
            assertThat(clusterStatusReport.getClusterStatus()).isEqualTo(ClusterStatus.STABLE);

            Sleep.sleepUninterruptibly(Duration.ofSeconds(10));

            // Verify data path is working fine
            for (int i = 0; i < DEFAULT_TABLE_ITER; i++) {
                assertThat(table.get(String.valueOf(i))).isEqualTo(String.valueOf(i));
            }

            corfuClient.shutdown();
        });
    }

}