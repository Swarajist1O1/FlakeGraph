class DummyClass_26240 {
    @Test
    public void testPostExecuteNotRepaired()
    {
        // mock
        doReturn(true).when(myRepairStateSnapshot).canRepair();

        long lastRun = myRepairJob.getLastSuccessfulRun();

        myRepairJob.postExecute(true, null);

        assertThat(myRepairJob.getLastSuccessfulRun()).isEqualTo(lastRun);
        verify(myRepairState, times(1)).update();
    }

}