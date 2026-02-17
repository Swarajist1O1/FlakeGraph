class DummyClass_26233 {
    @Test
    public void testPrevalidateNotRepairable()
    {
        // mock
        doReturn(false).when(myRepairStateSnapshot).canRepair();

        assertThat(myRepairJob.runnable()).isFalse();

        verify(myRepairState, times(1)).update();
        verify(myRepairStateSnapshot, times(1)).canRepair();
    }

}