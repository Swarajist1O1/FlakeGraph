class DummyClass_133989 {
    @Test
    public void testClusterHealth() {
        Layout layout = layoutUtil.getLayout(servers);
        layout.setUnresponsiveServers(Collections.singletonList(server3));

        //invalid log unit state
        ClusterStatus status = clusterHealth.getClusterHealth(
                layout, layout.getAllActiveServers()
        );
        assertThat(status).isEqualTo(ClusterStatus.UNAVAILABLE);

        //stable state with an unresponsive server
        layout.getFirstSegment().getFirstStripe().getLogServers().remove(server3);
        status = clusterHealth.getClusterHealth(
                layout, layout.getAllActiveServers()
        );
        assertThat(status).isEqualTo(ClusterStatus.DEGRADED);
    }

}