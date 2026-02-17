class DummyClass_26241 {
    @Test
    public void testPostExecuteNotRepairedWithFailure()
    {
        // mock
        doReturn(true).when(myRepairStateSnapshot).canRepair();

        long lastRun = myRepairJob.getLastSuccessfulRun();

        myRepairJob.postExecute(false, null);

        assertThat(myRepairJob.getLastSuccessfulRun()).isEqualTo(lastRun);
        verify(myRepairState, times(1)).update();
    }

}