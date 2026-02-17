class DummyClass_26150 {
    @Test
    public void testStatusEmpty()
    {
        when(myRepairScheduler.getCurrentRepairJobs()).thenReturn(new ArrayList<>());

        List<ScheduledRepairJob> response = GSON.fromJson(repairManagementREST.status(), scheduledRepairJobListType);

        assertThat(response).isEmpty();
    }

}