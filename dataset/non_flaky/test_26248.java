class DummyClass_26248 {
    @Test
    public void testStatusInQueue()
    {
        long repairedAt = System.currentTimeMillis() - TimeUnit.DAYS.toMillis(1);
        VnodeRepairState vnodeRepairState = TestUtils.createVnodeRepairState(1, 2, ImmutableSet.of(), repairedAt);
        VnodeRepairStatesImpl vnodeRepairStates = VnodeRepairStatesImpl.newBuilder(Arrays.asList(vnodeRepairState)).build();
        when(myRepairStateSnapshot.getVnodeRepairStates()).thenReturn(vnodeRepairStates);
        doReturn(repairedAt).when(myRepairStateSnapshot).lastCompletedAt();

        assertThat(myRepairJob.getView().getStatus()).isEqualTo(RepairJobView.Status.IN_QUEUE);
    }

}