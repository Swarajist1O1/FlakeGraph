class DummyClass_26212 {
    @Test
    public void testConfigureTwoTables()
    {
        RepairSchedulerImpl repairSchedulerImpl = defaultRepairSchedulerImplBuilder().build();

        repairSchedulerImpl.putConfiguration(TABLE_REFERENCE, RepairConfiguration.DEFAULT);
        repairSchedulerImpl.putConfiguration(TABLE_REFERENCE2, RepairConfiguration.DEFAULT);

        verify(scheduleManager, timeout(1000).times(2)).schedule(any(ScheduledJob.class));
        verify(scheduleManager, never()).deschedule(any(ScheduledJob.class));
        verify(myRepairStateFactory).create(eq(TABLE_REFERENCE), eq(RepairConfiguration.DEFAULT), any());
        verify(myRepairStateFactory).create(eq(TABLE_REFERENCE2), eq(RepairConfiguration.DEFAULT), any());
        verify(myRepairState, atLeastOnce()).update();

        repairSchedulerImpl.close();
        verify(scheduleManager, times(2)).deschedule(any(ScheduledJob.class));

        verifyNoMoreInteractions(ignoreStubs(myTableRepairMetrics));
        verifyNoMoreInteractions(myRepairStateFactory);
        verifyNoMoreInteractions(scheduleManager);
    }

}