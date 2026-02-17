class DummyClass_26238 {
    @Test
    public void testPostExecuteRepaired()
    {
        // mock
        long repairedAt = System.currentTimeMillis();
        doReturn(repairedAt).when(myRepairStateSnapshot).lastCompletedAt();
        doReturn(false).when(myRepairStateSnapshot).canRepair();

        myRepairJob.postExecute(true, null);

        assertThat(myRepairJob.getLastSuccessfulRun()).isEqualTo(repairedAt);
        verify(myRepairState, times(1)).update();
    }

}