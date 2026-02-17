class DummyClass_26228 {
    @Test (expected = EcChronosException.class)
    public void testScheduleRepairOnNonExistentTable() throws EcChronosException
    {
        OnDemandRepairSchedulerImpl repairScheduler = defaultOnDemandRepairSchedulerImplBuilder().build();
        when(metadata.getKeyspace(TABLE_REFERENCE.getKeyspace())).thenReturn(myKeyspaceMetadata);
        verify(scheduleManager, never()).schedule(any(ScheduledJob.class));
        repairScheduler.scheduleJob(TABLE_REFERENCE);
    }

}