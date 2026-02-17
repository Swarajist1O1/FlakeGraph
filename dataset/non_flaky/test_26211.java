class DummyClass_26211 {
    @Test
    public void testConfigureNewTable()
    {
        RepairSchedulerImpl repairSchedulerImpl = defaultRepairSchedulerImplBuilder().build();

        repairSchedulerImpl.putConfiguration(TABLE_REFERENCE, RepairConfiguration.DEFAULT);

        verify(scheduleManager, timeout(1000)).schedule(any(ScheduledJob.class));
        verify(scheduleManager, never()).deschedule(any(ScheduledJob.class));
        verify(myRepairStateFactory).create(eq(TABLE_REFERENCE), eq(RepairConfiguration.DEFAULT), any());
        verify(myRepairState, atLeastOnce()).update();
        assertOneTableViewExist(repairSchedulerImpl, TABLE_REFERENCE, RepairConfiguration.DEFAULT);

        repairSchedulerImpl.close();
        verify(scheduleManager).deschedule(any(ScheduledJob.class));

        verifyNoMoreInteractions(ignoreStubs(myTableRepairMetrics));
        verifyNoMoreInteractions(myRepairStateFactory);
        verifyNoMoreInteractions(scheduleManager);
    }

}