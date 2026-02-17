class DummyClass_26166 {
    @Test
    public void testConfigMultipleEntries()
    {
        // Given
        RepairConfiguration repairConfig = TestUtils.createRepairConfiguration(11, 2.2, 33, 44);
        RepairJobView repairJobView = new ScheduledRepairJobView(UUID.randomUUID(), myTableReferenceFactory.forTable("ks", "tbl"), repairConfig, null, Status.IN_QUEUE, 0);

        RepairConfiguration repairConfig2 = TestUtils.createRepairConfiguration(22, 3.3, 44, 55);
        RepairJobView repairJobView2 = new ScheduledRepairJobView(UUID.randomUUID(), myTableReferenceFactory.forTable("ks2", "tbl"), repairConfig2, null, Status.IN_QUEUE, 0);

        RepairJobView repairJobView3 = new OnDemandRepairJobView(UUID.randomUUID(), myTableReferenceFactory.forTable("ks", "tbl"), RepairConfiguration.DEFAULT, Status.IN_QUEUE, 0, System.currentTimeMillis());

        List<TableRepairConfig> expectedResponse = Arrays.asList(
                new TableRepairConfig(repairJobView),
                new TableRepairConfig(repairJobView2),
                new TableRepairConfig(repairJobView3)
        );

        when(myRepairScheduler.getCurrentRepairJobs()).thenReturn(Arrays.asList(repairJobView, repairJobView2, repairJobView3));

        List<TableRepairConfig> response = GSON.fromJson(repairManagementREST.config(), tableRepairConfigListType);

        assertThat(response).isEqualTo(expectedResponse);
    }

}