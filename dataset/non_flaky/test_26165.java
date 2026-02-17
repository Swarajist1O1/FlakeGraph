class DummyClass_26165 {
    @Test
    public void testConfigEntry()
    {
        // Given
        RepairConfiguration repairConfig = TestUtils.createRepairConfiguration(11, 2.2, 33, 44);
        RepairJobView repairJobView = new ScheduledRepairJobView(UUID.randomUUID(), myTableReferenceFactory.forTable("ks", "tbl"), repairConfig, null, Status.IN_QUEUE, 0);
        TableRepairConfig expectedResponse = new TableRepairConfig(repairJobView);

        when(myRepairScheduler.getCurrentRepairJobs()).thenReturn(Collections.singletonList(repairJobView));

        List<TableRepairConfig> response = GSON.fromJson(repairManagementREST.config(), tableRepairConfigListType);

        assertThat(response).containsExactly(expectedResponse);
    }

}