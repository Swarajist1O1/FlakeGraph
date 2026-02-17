class DummyClass_26164 {
    @Test
    public void testConfigEmpty()
    {
        when(myRepairScheduler.getCurrentRepairJobs()).thenReturn(new ArrayList<>());

        List<TableRepairConfig> response = GSON.fromJson(repairManagementREST.config(), tableRepairConfigListType);

        assertThat(response).isEmpty();
    }

}