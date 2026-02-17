class DummyClass_26235 {
    @Test
    public void testPrevalidateNotRepairableThenRepairable()
    {
        // mock
        doReturn(false).doReturn(true).when(myRepairStateSnapshot).canRepair();

        assertThat(myRepairJob.runnable()).isFalse();
        assertThat(myRepairJob.runnable()).isTrue();

        verify(myRepairState, times(2)).update();
        verify(myRepairStateSnapshot, times(2)).canRepair();
    }

}