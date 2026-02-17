class DummyClass_26246 {
    @Test
    public void testStatusCompleted()
    {
        long repairedAt = System.currentTimeMillis();
        doReturn(repairedAt).when(myRepairStateSnapshot).lastCompletedAt();
        VnodeRepairState vnodeRepairState = TestUtils.createVnodeRepairState(1, 2, ImmutableSet.of(), repairedAt);
        VnodeRepairStatesImpl vnodeRepairStates = VnodeRepairStatesImpl.newBuilder(Arrays.asList(vnodeRepairState)).build();
        when(myRepairStateSnapshot.getVnodeRepairStates()).thenReturn(vnodeRepairStates);

        assertThat(myRepairJob.getView().getStatus()).isEqualTo(RepairJobView.Status.COMPLETED);
    }

}