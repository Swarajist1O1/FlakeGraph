class DummyClass_178039 {
    @Test
    public void testDateRangesWithYearEqual() throws Throwable {

        long startTime = System.currentTimeMillis();

        GuidedDatePickerAction[] datePickerActions = setupDateActionsForMinAndMaxRangeTests();

        scrollToMinAndMaxDates(new int[] {0, 1, 2}, datePickerActions[2]);
        long executionTime = System.currentTimeMillis() - startTime;
        Log.d(TAG, "testDateRangesWithYearEqual() Execution time: " + executionTime);
        Thread.sleep(FINAL_WAIT);
    }

}