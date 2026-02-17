class DummyClass_26169 {
    @Test
    public void testKeyspaceConfigEntry()
    {
        RepairConfiguration repairConfig = TestUtils.createRepairConfiguration(11, 2.2, 33, 44);
        RepairJobView repairJobView = new ScheduledRepairJobView(UUID.randomUUID(), myTableReferenceFactory.forTable("ks", "tbl"), repairConfig, null, Status.IN_QUEUE, 0);

        TableRepairConfig expectedResponse = new TableRepairConfig(repairJobView);

        when(myRepairScheduler.getCurrentRepairJobs()).thenReturn(Collections.singletonList(repairJobView));

        List<TableRepairConfig> response = GSON.fromJson(repairManagementREST.keyspaceConfig("ks"), tableRepairConfigListType);

        assertThat(response).containsExactly(expectedResponse);
    }

}