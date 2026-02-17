class DummyClass_88865 {
    @Test
    public void testAssignPartitions() {
        List<List<ClusterNode>> nodes = Collections.singletonList(Collections.singletonList(mock(ClusterNode.class)));

        doReturn(nodes).when(affinityFunction).assignPartitions(any());

        List<List<ClusterNode>> resNodes = wrapper.assignPartitions(mock(AffinityFunctionContext.class));

        assertEquals(nodes, resNodes);
        verify(affinityFunction, times(1)).assignPartitions(any());
    }

}