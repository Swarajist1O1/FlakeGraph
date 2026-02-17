class DummyClass_133987 {
    @Test
    public void testSequencerServersHealth(){
        Layout layout = layoutUtil.getLayout(servers);
        layout.setUnresponsiveServers(Collections.singletonList(server3));

        ClusterStatus status = clusterHealth.getSequencerServersClusterHealth(
                layout, layout.getAllActiveServers()
        );
        assertThat(status).isEqualTo(ClusterStatus.STABLE);

        //Unresponsive sequencer
        layout.setUnresponsiveServers(Collections.singletonList(server1));
        status = clusterHealth.getSequencerServersClusterHealth(
                layout, layout.getAllActiveServers()
        );
        assertThat(status).isEqualTo(ClusterStatus.UNAVAILABLE);
    }

}