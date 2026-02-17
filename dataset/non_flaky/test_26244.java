class DummyClass_26244 {
    @Test
    public void testIterator()
    {
        LongTokenRange tokenRange = new LongTokenRange(0, 10);
        ImmutableSet<Node> replicas = ImmutableSet.of(mock(Node.class), mock(Node.class));
        ImmutableList<LongTokenRange> vnodes = ImmutableList.of(tokenRange);

        VnodeRepairStates vnodeRepairStates = VnodeRepairStatesImpl
                .newBuilder(ImmutableList.of(new VnodeRepairState(tokenRange, replicas, 1234L)))
                .build();
        ReplicaRepairGroup replicaRepairGroup = new ReplicaRepairGroup(replicas, vnodes);

        RepairStateSnapshot repairStateSnapshot = RepairStateSnapshot.newBuilder()
                .withReplicaRepairGroups(Collections.singletonList(replicaRepairGroup))
                .withLastCompletedAt(1234L)
                .withVnodeRepairStates(vnodeRepairStates)
                .build();
        when(myRepairState.getSnapshot()).thenReturn(repairStateSnapshot);

        Iterator<ScheduledTask> iterator = myRepairJob.iterator();

        ScheduledTask task = iterator.next();
        assertThat(task).isInstanceOf(RepairGroup.class);
        Collection<RepairTask> repairTasks = ((RepairGroup)task).getRepairTasks();

        assertThat(repairTasks).hasSize(1);
        RepairTask repairTask = repairTasks.iterator().next();
        assertThat(repairTask.getReplicas()).containsExactlyInAnyOrderElementsOf(replicas);
        assertThat(repairTask.getTokenRanges()).containsExactly(tokenRange);
        assertThat(repairTask.getRepairConfiguration()).isEqualTo(myRepairConfiguration);
        assertThat(repairTask.getTableReference()).isEqualTo(myTableReference);
    }

}