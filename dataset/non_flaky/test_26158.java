class DummyClass_26158 {
    @Test
    public void testTableEntry() throws UnknownHostException
    {
        long expectedLastRepairedAt = 234;
        long repairInterval = 123;
        Node replica = mock(Node.class);
        when(replica.getPublicAddress()).thenReturn(InetAddress.getLocalHost());

        VnodeRepairState vnodeRepairState = TestUtils
                .createVnodeRepairState(2, 3, ImmutableSet.of(replica), expectedLastRepairedAt);
        RepairJobView repairJobView = new TestUtils.ScheduledRepairJobBuilder()
                .withKeyspace("ks")
                .withTable("tb")
                .withLastRepairedAt(expectedLastRepairedAt)
                .withRepairInterval(repairInterval)
                .withVnodeRepairStateSet(ImmutableSet.of(vnodeRepairState))
                .withStatus(Status.IN_QUEUE)
                .build();

        List<ScheduledRepairJob> expectedResponse = Collections.singletonList(new ScheduledRepairJob(repairJobView));

        when(myRepairScheduler.getCurrentRepairJobs()).thenReturn(Collections.singletonList(repairJobView));

        List<ScheduledRepairJob> response = GSON.fromJson(repairManagementREST.tableStatus("ks", "tb"), scheduledRepairJobListType);

        assertThat(response).isEqualTo(expectedResponse);
    }

}