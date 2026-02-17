class DummyClass_26167 {
    @Test
    public void testKeyspaceConfigEmpty()
    {
        when(myRepairScheduler.getCurrentRepairJobs()).thenReturn(new ArrayList<>());

        List<TableRepairConfig> response = GSON.fromJson(repairManagementREST.keyspaceConfig(""), tableRepairConfigListType);

        assertThat(response).isEmpty();
    }

}