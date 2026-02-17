class DummyClass_133986 {
    @Test
    public void testLayoutServersHealth(){
        Layout layout = layoutUtil.getLayout(servers);
        ClusterStatus status = clusterHealth.getLayoutServersClusterHealth(
                layout, layout.getAllActiveServers()
        );
        assertThat(status).isEqualTo(ClusterStatus.STABLE);

        layout.setUnresponsiveServers(Collections.singletonList(server3));
        status = clusterHealth.getLayoutServersClusterHealth(
                layout, layout.getAllActiveServers()
        );
        assertThat(status).isEqualTo(ClusterStatus.DEGRADED);

        layout.setUnresponsiveServers(Arrays.asList(server2, server3));
        status = clusterHealth.getLayoutServersClusterHealth(
                layout, layout.getAllActiveServers()
        );
        assertThat(status).isEqualTo(ClusterStatus.UNAVAILABLE);
    }

}