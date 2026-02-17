class DummyClass_26217 {
    @Test
    public void testFailedJobCorrectlyReturned()
    {
        OnDemandRepairJob repairJob = createOnDemandRepairJob();
        Iterator<ScheduledTask> it = repairJob.iterator();
        repairJob.postExecute(false, it.next());
        RepairJobView expectedView = new OnDemandRepairJobView(repairJob.getId(), myTableReference, RepairConfiguration.DEFAULT, RepairJobView.Status.ERROR, 0, System.currentTimeMillis());
        assertThat(repairJob.getLastSuccessfulRun()).isEqualTo(-1);
        assertThat(repairJob.getRepairConfiguration()).isEqualTo(RepairConfiguration.DEFAULT);
        assertThat(repairJob.getTableReference()).isEqualTo(myTableReference);
        assertThat(repairJob.getView().getRepairConfiguration()).isEqualTo(expectedView.getRepairConfiguration());
        assertThat(repairJob.getView().getRepairStateSnapshot()).isNull();
        assertThat(repairJob.getView().getTableReference()).isEqualTo(expectedView.getTableReference());
        assertThat(repairJob.getView().getStatus()).isEqualTo(expectedView.getStatus());
    }

}