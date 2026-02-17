class DummyClass_26243 {
    @Test
    public void testGetView()
    {
        VnodeRepairState vnodeRepairState = TestUtils.createVnodeRepairState(1, 2, ImmutableSet.of(), System.currentTimeMillis());
        VnodeRepairStatesImpl vnodeRepairStates = VnodeRepairStatesImpl.newBuilder(Arrays.asList(vnodeRepairState)).build();
        when(myRepairStateSnapshot.getVnodeRepairStates()).thenReturn(vnodeRepairStates);
        RepairJobView repairJobView = myRepairJob.getView();

        assertThat(repairJobView.getId()).isEqualTo(myTableReference.getId());
        assertThat(repairJobView.getTableReference()).isEqualTo(myTableReference);
        assertThat(repairJobView.getRepairConfiguration()).isEqualTo(myRepairConfiguration);
        assertThat(repairJobView.getRepairStateSnapshot()).isEqualTo(myRepairStateSnapshot);
        assertThat(repairJobView.getStatus()).isEqualTo(RepairJobView.Status.ERROR);
    }

}