class DummyClass_26239 {
    @Test
    public void testPostExecuteRepairedWithFailure()
    {
        // mock
        long repairedAt = System.currentTimeMillis();
        doReturn(repairedAt).when(myRepairStateSnapshot).lastCompletedAt();
        doReturn(false).when(myRepairStateSnapshot).canRepair();

        myRepairJob.postExecute(false, null);

        assertThat(myRepairJob.getLastSuccessfulRun()).isEqualTo(repairedAt);
        verify(myRepairState, times(1)).update();
    }

}