class DummyClass_26153 {
    @Test
    public void testKeyspaceStatusEmpty()
    {
        when(myRepairScheduler.getCurrentRepairJobs()).thenReturn(new ArrayList<>());

        List<ScheduledRepairJob> response = GSON.fromJson(repairManagementREST.keyspaceStatus(""), scheduledRepairJobListType);

        assertThat(response).isEmpty();
    }

}