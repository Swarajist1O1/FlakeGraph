class DummyClass_26213 {
    @Test
    public void testRemoveTableConfiguration()
    {
        RepairSchedulerImpl repairSchedulerImpl = defaultRepairSchedulerImplBuilder().build();

        repairSchedulerImpl.putConfiguration(TABLE_REFERENCE, RepairConfiguration.DEFAULT);

        verify(scheduleManager, timeout(1000)).schedule(any(ScheduledJob.class));
        verify(scheduleManager, never()).deschedule(any(ScheduledJob.class));
        verify(myRepairStateFactory).create(eq(TABLE_REFERENCE), eq(RepairConfiguration.DEFAULT), any());
        verify(myRepairState, atLeastOnce()).update();
        assertOneTableViewExist(repairSchedulerImpl, TABLE_REFERENCE, RepairConfiguration.DEFAULT);

        repairSchedulerImpl.removeConfiguration(TABLE_REFERENCE);
        verify(scheduleManager, timeout(1000)).deschedule(any(ScheduledJob.class));
        assertThat(repairSchedulerImpl.getCurrentRepairJobs()).isEmpty();

        repairSchedulerImpl.close();
        verifyNoMoreInteractions(ignoreStubs(myTableRepairMetrics));
        verifyNoMoreInteractions(myRepairStateFactory);
        verifyNoMoreInteractions(scheduleManager);
    }

}