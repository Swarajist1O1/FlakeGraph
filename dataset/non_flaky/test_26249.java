class DummyClass_26249 {
    @Test
    public void testStatusWarning()
    {
        long repairedAt = System.currentTimeMillis() - TimeUnit.DAYS.toMillis(7);
        VnodeRepairState vnodeRepairState = TestUtils.createVnodeRepairState(1, 2, ImmutableSet.of(), repairedAt);
        VnodeRepairStatesImpl vnodeRepairStates = VnodeRepairStatesImpl.newBuilder(Arrays.asList(vnodeRepairState)).build();
        when(myRepairStateSnapshot.getVnodeRepairStates()).thenReturn(vnodeRepairStates);
        doReturn(repairedAt).when(myRepairStateSnapshot).lastCompletedAt();

        assertThat(myRepairJob.getView().getStatus()).isEqualTo(RepairJobView.Status.WARNING);
    }

}