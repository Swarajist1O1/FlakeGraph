class DummyClass_26175 {
    @Test
    public void testConfigIdEntryNotFound() throws UnknownHostException
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

        when(myRepairScheduler.getCurrentRepairJobs()).thenReturn(repairJobViews);

        String response = repairManagementREST.jobConfig(UUID.randomUUID().toString());

        assertThat(response).isEqualTo("{}");
    }

}