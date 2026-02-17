class DummyClass_26176 {
    @Test
    public void testConfigIdEntryEmpty()
    {
        when(myRepairScheduler.getCurrentRepairJobs()).thenReturn(Collections.emptyList());

        String response = repairManagementREST.jobConfig(UUID.randomUUID().toString());

        assertThat(response).isEqualTo("{}");
    }

}