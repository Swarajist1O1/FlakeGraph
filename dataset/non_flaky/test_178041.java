class DummyClass_178041 {
    @Test
    public void testDateRangesWithAllFieldsEqual() throws Throwable {

        long startTime = System.currentTimeMillis();

        GuidedDatePickerAction[] datePickerActions = setupDateActionsForMinAndMaxRangeTests();

        scrollToMinAndMaxDates(new int[] {0, 1, 2}, datePickerActions[4]);
        long executionTime = System.currentTimeMillis() - startTime;
        Log.d(TAG, "testDateRangesWithAllFieldsEqual() Execution time: " + executionTime);
        Thread.sleep(FINAL_WAIT);
    }

}