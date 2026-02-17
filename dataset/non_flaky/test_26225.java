class DummyClass_26225 {
    @Test
    public void testRestartRepairOnTable() throws EcChronosException
    {
        Set<OngoingJob> ongoingJobs = new HashSet<>();
        ongoingJobs.add(myOngingJob);
        when(myOnDemandStatus.getOngoingJobs(replicationState)).thenReturn(ongoingJobs);

        OnDemandRepairSchedulerImpl repairScheduler = defaultOnDemandRepairSchedulerImplBuilder().build();

        verify(scheduleManager, timeout(1000)).schedule(any(ScheduledJob.class));

        repairScheduler.close();
        verify(scheduleManager).deschedule(any(ScheduledJob.class));

        verifyNoMoreInteractions(ignoreStubs(myTableRepairMetrics));
        verifyNoMoreInteractions(scheduleManager);
    }

}