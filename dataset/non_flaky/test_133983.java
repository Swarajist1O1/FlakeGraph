class DummyClass_133983 {
    @Test
    public void testGetLayoutForQuorum() {
        final String server1 = "server1";
        final String server2 = "server2";
        final String server3 = "server3";

        final List<String> servers = Arrays.asList(server1, server2, server3);
        final Layout layout = layoutUtil.getLayout(servers);

        Map<String, Layout> layouts = new HashMap<>();

        servers.forEach(server -> layouts.put(server, layout));

        Optional<Layout> quorumLayout = managementView.getLayoutFromQuorum(layouts, layouts.size() - 1);
        assertThat(quorumLayout).isEqualTo(Optional.of(layout));

        quorumLayout = managementView.getLayoutFromQuorum(layouts, layouts.size());
        assertThat(quorumLayout).isEqualTo(Optional.of(layout));

        quorumLayout = managementView.getLayoutFromQuorum(layouts, layouts.size() + 1);
        assertThat(quorumLayout).isEqualTo(Optional.empty());
    }

}