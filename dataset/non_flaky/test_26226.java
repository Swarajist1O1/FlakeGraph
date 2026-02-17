class DummyClass_26226 {
    @Test
    public void testRestartRepairOnTableWithException() throws EcChronosException
    {
        Set<OngoingJob> ongoingJobs = new HashSet<>();
        ongoingJobs.add(myOngingJob);
        Map<EndPoint, Throwable> errors = new HashMap<>();
        when(myOnDemandStatus.getOngoingJobs(replicationState)).thenThrow(new NoHostAvailableException(errors )).thenReturn(ongoingJobs);

        OnDemandRepairSchedulerImpl repairScheduler = defaultOnDemandRepairSchedulerImplBuilder().build();

        verify(scheduleManager, timeout(15000)).schedule(any(ScheduledJob.class));

        repairScheduler.close();
        verify(scheduleManager).deschedule(any(ScheduledJob.class));

        verifyNoMoreInteractions(ignoreStubs(myTableRepairMetrics));
        verifyNoMoreInteractions(scheduleManager);
    }

}