class DummyClass_133988 {
    @Test
    public void testLogUnitServersClusterHealth(){
        Layout layout = layoutUtil.getLayout(servers);

        ClusterStatus status = clusterHealth.getLogUnitServersClusterHealth(
                layout, layout.getAllActiveServers()
        );
        assertThat(status).isEqualTo(ClusterStatus.STABLE);

        //invalid segment
        layout.setUnresponsiveServers(Collections.singletonList(server3));
        status = clusterHealth.getLogUnitServersClusterHealth(
                layout, layout.getAllActiveServers()
        );
        assertThat(status).isEqualTo(ClusterStatus.UNAVAILABLE);

        //exclude unresponsive server
        layout.getFirstSegment().getFirstStripe().getLogServers().remove(server3);
        status = clusterHealth.getLogUnitServersClusterHealth(
                layout, layout.getAllActiveServers()
        );
        assertThat(status).isEqualTo(ClusterStatus.STABLE);
    }

}