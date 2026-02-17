class DummyClass_26219 {
    @Test
    public void testJobFinishedAfterRestart()
    {
        OnDemandRepairJob repairJob = createRestartedOnDemandRepairJob();
        Iterator<ScheduledTask> it = repairJob.iterator();
        assertThat(repairJob.getState()).isEqualTo(ScheduledJob.State.RUNNABLE);
        repairJob.postExecute(true, it.next());
        assertThat(repairJob.getState()).isEqualTo(ScheduledJob.State.FINISHED);
    }

}