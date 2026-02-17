class DummyClass_133993 {
    @Test
    public void testConnectedAndFailedNodes() {
        NodeConnectivity nodeState = NodeConnectivity.connectivity(
                "a",
                ImmutableMap.of("a", OK, "b", OK, "c", FAILED)
        );

        assertThat(nodeState.getConnectedNodes()).isEqualTo(ImmutableSet.of("a", "b"));
        assertThat(nodeState.getFailedNodes()).isEqualTo(ImmutableSet.of("c"));
    }

}