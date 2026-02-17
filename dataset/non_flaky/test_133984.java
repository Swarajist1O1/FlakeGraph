class DummyClass_133984 {
    @Test
    public void testNodeStatusMap() {
        final String server1 = "server1";
        final String server2 = "server2";
        final String server3 = "server3";
        Layout layout = layoutUtil.getLayout(Arrays.asList(server1, server2, server3));
        layout.setUnresponsiveServers(Arrays.asList(server1, server2));

        Map<String, NodeStatus> status = managementView.getNodeStatusMap(layout);
        assertThat(status.get(server1)).isEqualTo(NodeStatus.DOWN);
        assertThat(status.get(server2)).isEqualTo(NodeStatus.DOWN);
        assertThat(status.get(server3)).isEqualTo(NodeStatus.UP);
    }

}