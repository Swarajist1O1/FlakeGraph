class DummyClass_26227 {
    @Test (expected = EcChronosException.class)
    public void testScheduleRepairOnNonExistentKeyspaceTable() throws EcChronosException
    {
        OnDemandRepairSchedulerImpl repairScheduler = defaultOnDemandRepairSchedulerImplBuilder().build();

        verify(scheduleManager, never()).schedule(any(ScheduledJob.class));
        repairScheduler.scheduleJob(TABLE_REFERENCE);
    }

}