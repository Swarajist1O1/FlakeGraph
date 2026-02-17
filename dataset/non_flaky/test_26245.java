class DummyClass_26245 {
    @Test
    public void testIteratorWithTargetSize()
    {
        List<LongTokenRange> expectedTokenRanges = Arrays.asList(
                new LongTokenRange(0, 1),
                new LongTokenRange(1, 2),
                new LongTokenRange(2, 3),
                new LongTokenRange(3, 4),
                new LongTokenRange(4, 5),
                new LongTokenRange(5, 6),
                new LongTokenRange(6, 7),
                new LongTokenRange(7, 8),
                new LongTokenRange(8, 9),
                new LongTokenRange(9, 10)
        );

        LongTokenRange tokenRange = new LongTokenRange(0, 10);
        ImmutableSet<Node> replicas = ImmutableSet.of(mock(Node.class), mock(Node.class));
        ImmutableList<LongTokenRange> vnodes = ImmutableList.of(tokenRange);

        VnodeRepairStates vnodeRepairStates = VnodeRepairStatesImpl.newBuilder(ImmutableList.of(new VnodeRepairState(tokenRange, replicas, 1234L))).build();
        ReplicaRepairGroup replicaRepairGroup = new ReplicaRepairGroup(replicas, vnodes);

        RepairStateSnapshot repairStateSnapshot = RepairStateSnapshot.newBuilder()
                .withReplicaRepairGroups(Collections.singletonList(replicaRepairGroup))
                .withLastCompletedAt(1234L)
                .withVnodeRepairStates(vnodeRepairStates)
                .build();
        when(myRepairState.getSnapshot()).thenReturn(repairStateSnapshot);
        // 100 MB target size, 1000MB in table
        when(myTableStorageStates.getDataSize(eq(myTableReference))).thenReturn(THOUSAND_MB_IN_BYTES);

        Iterator<ScheduledTask> iterator = myRepairJob.iterator();

        ScheduledTask task = iterator.next();
        assertThat(task).isInstanceOf(RepairGroup.class);
        Collection<RepairTask> repairTasks = ((RepairGroup)task).getRepairTasks();

        assertThat(repairTasks).hasSize(expectedTokenRanges.size());

        Iterator<RepairTask> repairTaskIterator = repairTasks.iterator();
        for (LongTokenRange expectedRange : expectedTokenRanges)
        {
            assertThat(repairTaskIterator.hasNext()).isTrue();
            RepairTask repairTask = repairTaskIterator.next();
            assertThat(repairTask.getReplicas()).containsExactlyInAnyOrderElementsOf(replicas);
            assertThat(repairTask.getRepairConfiguration()).isEqualTo(myRepairConfiguration);
            assertThat(repairTask.getTableReference()).isEqualTo(myTableReference);

            assertThat(repairTask.getTokenRanges()).containsExactly(expectedRange);
        }
    }

}