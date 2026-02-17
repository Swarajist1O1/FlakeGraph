class DummyClass_26154 {
    @Test
    public void testKeyspaceStatusNonExisting()
    {
        long expectedLastRepairedAt = 234;
        long expectedRepairInterval = 123;

        RepairJobView repairJobView = new TestUtils.ScheduledRepairJobBuilder()
                .withKeyspace("ks")
                .withTable("tb")
                .withLastRepairedAt(expectedLastRepairedAt)
                .withRepairInterval(expectedRepairInterval)
                .build();

        when(myRepairScheduler.getCurrentRepairJobs()).thenReturn(Collections.singletonList(repairJobView));

        List<ScheduledRepairJob> response = GSON.fromJson(repairManagementREST.keyspaceStatus("nonexistingkeyspace"), scheduledRepairJobListType);

        assertThat(response).isEmpty();
    }

}