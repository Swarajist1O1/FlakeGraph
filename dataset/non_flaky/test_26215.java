class DummyClass_26215 {
    @Test
    public void testUpdateTableConfigurationToSame()
    {
        RepairSchedulerImpl repairSchedulerImpl = defaultRepairSchedulerImplBuilder().build();

        repairSchedulerImpl.putConfiguration(TABLE_REFERENCE, RepairConfiguration.DEFAULT);

        verify(scheduleManager, timeout(1000)).schedule(any(ScheduledJob.class));
        verify(scheduleManager, never()).deschedule(any(ScheduledJob.class));
        verify(myRepairStateFactory).create(eq(TABLE_REFERENCE), eq(RepairConfiguration.DEFAULT), any());
        verify(myRepairState, atLeastOnce()).update();
        assertOneTableViewExist(repairSchedulerImpl, TABLE_REFERENCE, RepairConfiguration.DEFAULT);

        repairSchedulerImpl.putConfiguration(TABLE_REFERENCE, RepairConfiguration.DEFAULT);

        assertOneTableViewExist(repairSchedulerImpl, TABLE_REFERENCE, RepairConfiguration.DEFAULT);

        repairSchedulerImpl.close();
        verify(scheduleManager).deschedule(any(ScheduledJob.class));
        assertThat(repairSchedulerImpl.getCurrentRepairJobs()).isEmpty();

        verifyNoMoreInteractions(ignoreStubs(myTableRepairMetrics));
        verifyNoMoreInteractions(myRepairStateFactory);
        verifyNoMoreInteractions(scheduleManager);
    }

}