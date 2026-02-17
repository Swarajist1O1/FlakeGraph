class DummyClass_26171 {
    @Test
    public void testTableConfigEmpty()
    {
        when(myRepairScheduler.getCurrentRepairJobs()).thenReturn(new ArrayList<>());

        Map<Object, Object> response = GSON.fromJson(repairManagementREST.tableConfig("ks", "tbl"), new TypeToken<Map<Object, Object>>(){}.getType());

        assertThat(response).isEmpty();
    }

}