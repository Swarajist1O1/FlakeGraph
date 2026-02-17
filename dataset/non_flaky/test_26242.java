class DummyClass_26242 {
    @Test
    public void testPostExecuteUpdateThrowsException()
    {
        // mock
        doThrow(new RuntimeException("Expected exception")).when(myRepairState).update();

        long lastRun = myRepairJob.getLastSuccessfulRun();

        myRepairJob.postExecute(true, null);

        assertThat(myRepairJob.getLastSuccessfulRun()).isEqualTo(lastRun);
    }

}