class DummyClass_26174 {
    @Test
    public void testConfigIdEntry() throws UnknownHostException
    {
        Host host = mock(Host.class);
        when(host.getBroadcastAddress()).thenReturn(InetAddress.getLocalHost());
        UUID expectedId = UUID.randomUUID();
        RepairJobView expectedRepairJob = new TestUtils.ScheduledRepairJobBuilder()
                .withId(expectedId)
                .withKeyspace("ks")
                .withTable("tb")
                .withLastRepairedAt(1234L)
                .withRepairInterval(11)
                .build();
        RepairJobView job1 = new TestUtils.ScheduledRepairJobBuilder()
                .withKeyspace("ks")
                .withTable("tb2")
                .withLastRepairedAt(134L)
                .withRepairInterval(112)
                .build();
        RepairJobView job2 = new TestUtils.ScheduledRepairJobBuilder()
                .withKeyspace("ks")
                .withTable("tb")
                .withLastRepairedAt(132L)
                .withRepairInterval(132)
                .build();
        RepairJobView job3 = new TestUtils.OnDemandRepairJobBuilder()
                .withKeyspace("ks")
                .withTable("tb2")
                .withCompletedAt(3456L)
                .build();

        TableRepairConfig expectedResponse = new TableRepairConfig(expectedRepairJob);

        List<RepairJobView> repairJobViews = Arrays.asList(
                expectedRepairJob,
                job3,
                job1,
                job2
        );

        when(myRepairScheduler.getCurrentRepairJobs()).thenReturn(repairJobViews);

        TableRepairConfig response = GSON.fromJson(repairManagementREST.jobConfig(expectedId.toString()), TableRepairConfig.class);
        assertThat(response).isEqualTo(expectedResponse);
    }

}