class DummyClass_26152 {
    @Test
    public void testStatusMultipleEntries()
    {
        RepairJobView job1 = new TestUtils.ScheduledRepairJobBuilder()
                .withKeyspace("ks")
                .withTable("tb")
                .withLastRepairedAt(1234L)
                .withRepairInterval(11)
                .build();
        RepairJobView job2 = new TestUtils.ScheduledRepairJobBuilder()
                .withKeyspace("ks")
                .withTable("tb2")
                .withLastRepairedAt(2345L)
                .withRepairInterval(12)
                .build();
        RepairJobView job3 = new TestUtils.OnDemandRepairJobBuilder()
                .withKeyspace("ks")
                .withTable("tb2")
                .withCompletedAt(3456L)
                .build();
        List<RepairJobView> repairJobViews = Arrays.asList(
                job1,
                job2,
                job3
        );

        List<ScheduledRepairJob> expectedResponse = repairJobViews.stream()
                .map(ScheduledRepairJob::new)
                .collect(Collectors.toList());

        when(myRepairScheduler.getCurrentRepairJobs()).thenReturn(repairJobViews);

        List<ScheduledRepairJob> response = GSON.fromJson(repairManagementREST.status(), scheduledRepairJobListType);

        assertThat(response).isEqualTo(expectedResponse);
    }

}