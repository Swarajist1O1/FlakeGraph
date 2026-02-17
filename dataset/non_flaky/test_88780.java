class DummyClass_88780 {
    @Test
    public void testStart() {
        UUID nodeId = UUID.randomUUID();
        UUID procId = UUID.randomUUID();

        Ignite ignite = mock(Ignite.class);
        IgniteCluster cluster = mock(IgniteCluster.class);
        ClusterGroup clusterGrp = mock(ClusterGroup.class);
        IgniteCompute igniteCompute = mock(IgniteCompute.class);
        doReturn(cluster).when(ignite).cluster();
        doReturn(igniteCompute).when(ignite).compute(eq(clusterGrp));
        doReturn(clusterGrp).when(cluster).forNodeId(eq(nodeId));
        doReturn(Collections.singletonList(procId)).when(igniteCompute).call(any(IgniteCallable.class));

        List<LongRunningProcess> list = Collections.singletonList(new LongRunningProcess(nodeId, () -> {}));

        LongRunningProcessManager mgr = new LongRunningProcessManager(ignite);
        Map<UUID, List<UUID>> res = mgr.start(list);

        assertEquals(1, res.size());
        assertTrue(res.containsKey(nodeId));
        assertEquals(procId, res.get(nodeId).iterator().next());

        verify(igniteCompute).call(any(LongRunningProcessStartTask.class));
    }

}