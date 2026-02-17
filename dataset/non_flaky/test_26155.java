class DummyClass_26155 {
    @Test
    public void testKeyspaceStatusEntry()
    {
        long expectedLastRepairedAt = 234;
        long repairInterval = 123;

        RepairJobView repairJobView = new TestUtils.ScheduledRepairJobBuilder()
            .withKeyspace("ks")
            .withTable("tb")
            .withLastRepairedAt(expectedLastRepairedAt)
            .withRepairInterval(repairInterval)
            .build();
        ScheduledRepairJob expectedResponse = new ScheduledRepairJob(repairJobView);

        when(myRepairScheduler.getCurrentRepairJobs()).thenReturn(Collections.singletonList(repairJobView));

        List<ScheduledRepairJob> response = GSON.fromJson(repairManagementREST.keyspaceStatus("ks"), scheduledRepairJobListType);

        assertThat(response).containsExactly(expectedResponse);
    }

}