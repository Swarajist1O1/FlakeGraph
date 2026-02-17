class DummyClass_26157 {
    @Test
    public void testTableNonExisting()
    {
        when(myRepairScheduler.getCurrentRepairJobs()).thenReturn(new ArrayList<>());

        Map<Object, Object> response = GSON.fromJson(repairManagementREST.tableStatus("ks", "tb"), new TypeToken<Map<Object, Object>>(){}.getType());

        assertThat(response).isEmpty();
    }

}