class DummyClass_178038 {
    @Test
    public void testDateRangesMDYFormat() throws Throwable {

        long startTime = System.currentTimeMillis();

        GuidedDatePickerAction[] datePickerActions = setupDateActionsForMinAndMaxRangeTests();

        scrollToMinAndMaxDates(new int[] {1, 0, 2}, datePickerActions[0]);
        long executionTime = System.currentTimeMillis() - startTime;
        Log.d(TAG, "testDateRangesMDYFormat() Execution time: " + executionTime);
        Thread.sleep(FINAL_WAIT);
    }

}