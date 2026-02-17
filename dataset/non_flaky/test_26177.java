class DummyClass_26177 {
    @Test
    public void testConfigIdInvalidUUID()
    {
        when(myRepairScheduler.getCurrentRepairJobs()).thenReturn(Collections.emptyList());

        String response = repairManagementREST.jobConfig("123");

        assertThat(response).isEqualTo("{}");
    }

}