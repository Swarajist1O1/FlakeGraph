class DummyClass_26159 {
    @Test
    public void testTableMultipleEntries() throws UnknownHostException
    {
        Host host = mock(Host.class);
        when(host.getBroadcastAddress()).thenReturn(InetAddress.getLocalHost());
        RepairJobView job1 = new TestUtils.ScheduledRepairJobBuilder()
                .withKeyspace("ks")
                .withTable("tb")
                .withLastRepairedAt(1234L)
                .withRepairInterval(11)
                .build();
        RepairJobView job2 = new TestUtils.ScheduledRepairJobBuilder()
                .withKeyspace("ks")
                .withTable("tb2")
                .withLastRepairedAt(134L)
                .withRepairInterval(112)
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
                .filter(job -> "tb".equals(job.getTableReference().getTable()))
                .map(ScheduledRepairJob::new)
                .collect(Collectors.toList());

        when(myRepairScheduler.getCurrentRepairJobs()).thenReturn(repairJobViews);

        List<ScheduledRepairJob> response = GSON.fromJson(repairManagementREST.tableStatus("ks", "tb"), scheduledRepairJobListType);

        assertThat(response).isEqualTo(expectedResponse);
    }

}