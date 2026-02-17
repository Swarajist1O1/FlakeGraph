class DummyClass_178040 {
    @Test
    public void testDateRangesWithMonthAndYearEqual() throws Throwable {

        long startTime = System.currentTimeMillis();

        GuidedDatePickerAction[] datePickerActions = setupDateActionsForMinAndMaxRangeTests();

        scrollToMinAndMaxDates(new int[] {0, 1, 2}, datePickerActions[3]);
        long executionTime = System.currentTimeMillis() - startTime;
        Log.d(TAG, "testDateRangesWithMonthAndYearEqual() Execution time: " + executionTime);
        Thread.sleep(FINAL_WAIT);
    }

}