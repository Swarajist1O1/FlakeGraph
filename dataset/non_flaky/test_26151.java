class DummyClass_26151 {
    @Test
    public void testStatusEntry()
    {
        long repairInterval = TimeUnit.DAYS.toMillis(7);
        long lastRepairedAt = System.currentTimeMillis();

        RepairJobView repairJobView = new TestUtils.ScheduledRepairJobBuilder()
                .withKeyspace("ks")
                .withTable("tb")
                .withLastRepairedAt(lastRepairedAt)
                .withRepairInterval(repairInterval)
                .build();
        ScheduledRepairJob expectedResponse = new ScheduledRepairJob(repairJobView);

        when(myRepairScheduler.getCurrentRepairJobs()).thenReturn(Collections.singletonList(repairJobView));

        List<ScheduledRepairJob> response = GSON.fromJson(repairManagementREST.status(), scheduledRepairJobListType);

        assertThat(response).containsExactly(expectedResponse);
    }

}