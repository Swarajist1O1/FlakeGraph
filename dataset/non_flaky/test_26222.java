class DummyClass_26222 {
    @Test
    public void testGetProgress()
    {
        OnDemandRepairJob repairJob = createOnDemandRepairJob();
        assertThat(repairJob.getProgress()).isEqualTo(0);
        Iterator<ScheduledTask> it = repairJob.iterator();
        repairJob.postExecute(true, it.next());
        assertThat(repairJob.getProgress()).isEqualTo(0.5);
        repairJob.postExecute(true, it.next());
        assertThat(repairJob.getProgress()).isEqualTo(1);
    }

}