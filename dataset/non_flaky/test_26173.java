class DummyClass_26173 {
    @Test
    public void testTableConfigEntry()
    {
        RepairConfiguration repairConfig = TestUtils.createRepairConfiguration(11, 2.2, 33, 44);
        RepairJobView repairJobView = new ScheduledRepairJobView(UUID.randomUUID(), myTableReferenceFactory.forTable("ks", "tbl"), repairConfig, null, Status.IN_QUEUE, 0);

        List<TableRepairConfig> expectedResponse = Collections.singletonList(new TableRepairConfig(repairJobView));

        when(myRepairScheduler.getCurrentRepairJobs()).thenReturn(Collections.singletonList(repairJobView));

        List<TableRepairConfig> response = GSON.fromJson(repairManagementREST.tableConfig("ks", "tbl"), tableRepairConfigListType);

        assertThat(response).isEqualTo(expectedResponse);
    }

}