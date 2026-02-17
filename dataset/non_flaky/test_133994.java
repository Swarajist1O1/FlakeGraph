class DummyClass_133994 {
    @Test
    public void testFullNodeName() {
        final String clusterName = "mycluster";
        final int port = ServerUtil.getRandomOpenPort();

        CorfuServerParams param = CorfuServerParams
                .serverParamsBuilder()
                .port(port)
                .clusterName(clusterName)
                .serverVersion("1.0.0")
                .build();

        SortedSet<CorfuServerParams> corfuServers = new TreeSet<>(Collections.singletonList(param));

        CorfuClusterParams clusterParams = CorfuClusterParams.builder()
                .name(clusterName)
                .nodes(corfuServers)
                .serverVersion("1.0.0")
                .build();

        String fqdn = clusterParams.getFullNodeName("node" + port);

        assertThat(fqdn).isEqualTo(clusterName + "-corfu-" + "node" + port);
    }

}