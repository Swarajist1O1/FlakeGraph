class DummyClass_26168 {
    @Test
    public void testKeyspaceConfigNonExisting()
    {
        RepairConfiguration repairConfig = TestUtils.createRepairConfiguration(11, 2.2, 33, 44);
        RepairJobView repairJobView = new ScheduledRepairJobView(UUID.randomUUID(), myTableReferenceFactory.forTable("ks", "tbl"), repairConfig, null, Status.IN_QUEUE, 0);

        when(myRepairScheduler.getCurrentRepairJobs()).thenReturn(Collections.singletonList(repairJobView));

        List<TableRepairConfig> response = GSON.fromJson(repairManagementREST.keyspaceConfig("nonexistingkeyspace"), tableRepairConfigListType);

        assertThat(response).isEmpty();
    }

}