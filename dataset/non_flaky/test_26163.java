class DummyClass_26163 {
    @Test
    public void testIdInvalidUUID()
    {
        when(myRepairScheduler.getCurrentRepairJobs()).thenReturn(Collections.emptyList());

        String response = repairManagementREST.jobStatus("123");

        assertThat(response).isEqualTo("{}");
    }

}