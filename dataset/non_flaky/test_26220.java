class DummyClass_26220 {
    @Test
    public void testJobFailedWhenTopologyChange()
    {
        OnDemandRepairJob repairJob = createOnDemandRepairJob();
        when(myOngoingJob.hasTopologyChanged()).thenReturn(true);
        assertThat(repairJob.getState()).isEqualTo(ScheduledJob.State.FAILED);
    }

}