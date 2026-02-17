class DummyClass_26224 {
    @Test
    public void testScheduleTwoRepairOnTable() throws EcChronosException
    {
        OnDemandRepairSchedulerImpl repairScheduler = defaultOnDemandRepairSchedulerImplBuilder().build();
        when(metadata.getKeyspace(TABLE_REFERENCE.getKeyspace())).thenReturn(myKeyspaceMetadata);
        when(myKeyspaceMetadata.getTable(TABLE_REFERENCE.getTable())).thenReturn(myTableMetadata);

        verify(scheduleManager, never()).schedule(any(ScheduledJob.class));
        RepairJobView repairJobView = repairScheduler.scheduleJob(TABLE_REFERENCE);
        RepairJobView repairJobView2 = repairScheduler.scheduleJob(TABLE_REFERENCE);
        verify(scheduleManager, times(2)).schedule(any(ScheduledJob.class));

        assertTableViewExist(repairScheduler, repairJobView, repairJobView2);

        repairScheduler.close();
        verify(scheduleManager, times(2)).deschedule(any(ScheduledJob.class));

        verifyNoMoreInteractions(ignoreStubs(myTableRepairMetrics));
        verifyNoMoreInteractions(scheduleManager);
    }

}