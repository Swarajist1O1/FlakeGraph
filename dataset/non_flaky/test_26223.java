class DummyClass_26223 {
    @Test
    public void testScheduleRepairOnTable() throws EcChronosException
    {
        OnDemandRepairSchedulerImpl repairScheduler = defaultOnDemandRepairSchedulerImplBuilder().build();
        when(metadata.getKeyspace(TABLE_REFERENCE.getKeyspace())).thenReturn(myKeyspaceMetadata);
        when(myKeyspaceMetadata.getTable(TABLE_REFERENCE.getTable())).thenReturn(myTableMetadata);

        verify(scheduleManager, never()).schedule(any(ScheduledJob.class));
        RepairJobView repairJobView = repairScheduler.scheduleJob(TABLE_REFERENCE);
        verify(scheduleManager).schedule(any(ScheduledJob.class));

        assertTableViewExist(repairScheduler, repairJobView);

        repairScheduler.close();
        verify(scheduleManager).deschedule(any(ScheduledJob.class));

        verifyNoMoreInteractions(ignoreStubs(myTableRepairMetrics));
        verifyNoMoreInteractions(scheduleManager);
    }

}