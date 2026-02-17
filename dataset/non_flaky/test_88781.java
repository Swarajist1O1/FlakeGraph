class DummyClass_88781 {
    @Test
    public void testPing() {
        UUID nodeId = UUID.randomUUID();
        UUID procId = UUID.randomUUID();

        Ignite ignite = mock(Ignite.class);
        IgniteCluster cluster = mock(IgniteCluster.class);
        ClusterGroup clusterGrp = mock(ClusterGroup.class);
        IgniteCompute igniteCompute = mock(IgniteCompute.class);
        doReturn(cluster).when(ignite).cluster();
        doReturn(igniteCompute).when(ignite).compute(eq(clusterGrp));
        doReturn(clusterGrp).when(cluster).forNodeId(eq(nodeId));
        doReturn(Collections.singletonList(new LongRunningProcessStatus(LongRunningProcessState.RUNNING)))
            .when(igniteCompute).call(any(IgniteCallable.class));

        Map<UUID, List<UUID>> procIds = new HashMap<>();
        procIds.put(nodeId, Collections.singletonList(procId));

        LongRunningProcessManager mgr = new LongRunningProcessManager(ignite);
        Map<UUID, List<LongRunningProcessStatus>> res = mgr.ping(procIds);

        assertEquals(1, res.size());
        assertTrue(res.containsKey(nodeId));
        assertEquals(LongRunningProcessState.RUNNING, res.get(nodeId).iterator().next().getState());

        verify(igniteCompute).call(any(LongRunningProcessPingTask.class));
    }

}