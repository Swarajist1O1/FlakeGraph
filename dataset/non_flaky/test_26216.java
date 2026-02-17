class DummyClass_26216 {
    @Test
    public void testJobCorrectlyReturned()
    {
        OnDemandRepairJob repairJob = createOnDemandRepairJob();
        RepairJobView expectedView = new OnDemandRepairJobView(repairJob.getId(), myTableReference, RepairConfiguration.DEFAULT, RepairJobView.Status.IN_QUEUE, 0, System.currentTimeMillis());
        assertThat(repairJob.getId()).isEqualTo(repairJob.getId());
        assertThat(repairJob.getLastSuccessfulRun()).isEqualTo(-1);
        assertThat(repairJob.getRepairConfiguration()).isEqualTo(RepairConfiguration.DEFAULT);
        assertThat(repairJob.getTableReference()).isEqualTo(myTableReference);
        assertThat(repairJob.getView().getRepairConfiguration()).isEqualTo(expectedView.getRepairConfiguration());
        assertThat(repairJob.getView().getRepairStateSnapshot()).isNull();
        assertThat(repairJob.getView().getTableReference()).isEqualTo(expectedView.getTableReference());
        assertThat(repairJob.getView().getStatus()).isEqualTo(expectedView.getStatus());
    }

}