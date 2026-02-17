class DummyClass_26229 {
    @Test (expected = EcChronosException.class)
    public void testScheduleRepairOnNull() throws EcChronosException
    {
        OnDemandRepairSchedulerImpl repairScheduler = defaultOnDemandRepairSchedulerImplBuilder().build();
        verify(scheduleManager, never()).schedule(any(ScheduledJob.class));
        repairScheduler.scheduleJob(null);
    }

}