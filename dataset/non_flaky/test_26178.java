class DummyClass_26178 {
    @Test
    public void testScheduleRepair() throws EcChronosException
    {
        long expectedLastRepairedAt = 234;
        long repairInterval = 123;

        RepairJobView repairJobView = new TestUtils.ScheduledRepairJobBuilder()
                .withKeyspace("ks")
                .withTable("tb")
                .withLastRepairedAt(expectedLastRepairedAt)
                .withRepairInterval(repairInterval)
                .build();
        ScheduledRepairJob expectedResponse = new ScheduledRepairJob(repairJobView);

        when(myOnDemandRepairScheduler.scheduleJob(myTableReferenceFactory.forTable("ks","tb"))).thenReturn(repairJobView);
        ScheduledRepairJob response = GSON.fromJson(repairManagementREST.scheduleJob("ks", "tb"), ScheduledRepairJob.class);
        assertThat(response).isEqualTo(expectedResponse);
    }

}