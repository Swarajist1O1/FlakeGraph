class DummyClass_26162 {
    @Test
    public void testIdEntryEmpty()
    {
        when(myRepairScheduler.getCurrentRepairJobs()).thenReturn(Collections.emptyList());

        String response = repairManagementREST.jobStatus(UUID.randomUUID().toString());

        assertThat(response).isEqualTo("{}");
    }

}