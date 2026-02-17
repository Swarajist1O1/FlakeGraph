class DummyClass_26170 {
    @Test
    public void testKeyspaceConfigMultipleEntries()
    {
        RepairConfiguration repairConfig = TestUtils.createRepairConfiguration(11, 2.2, 33, 44);
        RepairJobView repairJobView = new ScheduledRepairJobView(UUID.randomUUID(), myTableReferenceFactory.forTable("ks", "tbl"), repairConfig, null, Status.IN_QUEUE, 0);

        RepairConfiguration repairConfig2 = TestUtils.createRepairConfiguration(22, 3.3, 44, 55);
        RepairJobView repairJobView2 = new ScheduledRepairJobView(UUID.randomUUID(), myTableReferenceFactory.forTable("ks", "tbl2"), repairConfig2, null, Status.IN_QUEUE, 0);

        List<TableRepairConfig> expectedResponse = Arrays.asList(
                new TableRepairConfig(repairJobView),
                new TableRepairConfig(repairJobView2)
        );

        when(myRepairScheduler.getCurrentRepairJobs()).thenReturn(Arrays.asList(repairJobView, repairJobView2));

        List<TableRepairConfig> response = GSON.fromJson(repairManagementREST.keyspaceConfig("ks"), tableRepairConfigListType);

        assertThat(response).isEqualTo(expectedResponse);
    }

}