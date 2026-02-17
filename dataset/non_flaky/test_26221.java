class DummyClass_26221 {
    @Test
    public void testJobUnsuccessful()
    {
        OnDemandRepairJob repairJob = createOnDemandRepairJob();
        Iterator<ScheduledTask> it = repairJob.iterator();
        repairJob.postExecute(true, it.next());
        assertThat(repairJob.getState()).isEqualTo(ScheduledJob.State.RUNNABLE);
        repairJob.postExecute(false, it.next());
        assertThat(repairJob.getState()).isEqualTo(ScheduledJob.State.FAILED);
    }

}