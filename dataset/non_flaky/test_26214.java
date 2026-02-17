class DummyClass_26214 {
    @Test
    public void testUpdateTableConfiguration()
    {
        RepairSchedulerImpl repairSchedulerImpl = defaultRepairSchedulerImplBuilder().build();

        long expectedUpdatedRepairInterval = TimeUnit.DAYS.toMillis(1);

        RepairConfiguration updatedRepairConfiguration = RepairConfiguration.newBuilder()
                .withRepairInterval(expectedUpdatedRepairInterval, TimeUnit.MILLISECONDS)
                .build();

        repairSchedulerImpl.putConfiguration(TABLE_REFERENCE, RepairConfiguration.DEFAULT);

        verify(scheduleManager, timeout(1000)).schedule(any(ScheduledJob.class));
        verify(scheduleManager, never()).deschedule(any(ScheduledJob.class));
        verify(myRepairStateFactory).create(eq(TABLE_REFERENCE), eq(RepairConfiguration.DEFAULT), any());
        verify(myRepairState, atLeastOnce()).update();
        assertOneTableViewExist(repairSchedulerImpl, TABLE_REFERENCE, RepairConfiguration.DEFAULT);

        repairSchedulerImpl.putConfiguration(TABLE_REFERENCE, updatedRepairConfiguration);

        verify(scheduleManager, timeout(1000).times(2)).schedule(any(ScheduledJob.class));
        verify(scheduleManager, timeout(1000)).deschedule(any(ScheduledJob.class));
        verify(myRepairStateFactory).create(eq(TABLE_REFERENCE), eq(updatedRepairConfiguration), any());
        verify(myRepairState, atLeastOnce()).update();
        assertOneTableViewExist(repairSchedulerImpl, TABLE_REFERENCE, updatedRepairConfiguration);

        repairSchedulerImpl.close();
        verify(scheduleManager, times(2)).deschedule(any(ScheduledJob.class));
        assertThat(repairSchedulerImpl.getCurrentRepairJobs()).isEmpty();

        verifyNoMoreInteractions(ignoreStubs(myTableRepairMetrics));
        verifyNoMoreInteractions(myRepairStateFactory);
        verifyNoMoreInteractions(scheduleManager);
    }

}