class DummyClass_26172 {
    @Test
    public void testTableConfigNonExisting()
    {
        RepairConfiguration repairConfig = TestUtils.createRepairConfiguration(11, 2.2, 33, 44);
        RepairJobView repairJobView = new ScheduledRepairJobView(UUID.randomUUID(), myTableReferenceFactory.forTable("ks", "tbl"), repairConfig, null, Status.IN_QUEUE, 0);

        when(myRepairScheduler.getCurrentRepairJobs()).thenReturn(Collections.singletonList(repairJobView));

        Map<Object, Object> response = GSON.fromJson(repairManagementREST.tableConfig("nonexisting", "tbl"), new TypeToken<Map<Object, Object>>(){}.getType());

        assertThat(response).isEmpty();
    }

}