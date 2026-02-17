class DummyClass_156463 {
    @Test
    public void testHoursOfDayWithDate() {
        long testResult = DateUtils.getFragmentInHours(aDate, Calendar.DATE);
        final long expectedValue = hours;
        assertEquals(expectedValue, testResult);
        testResult = DateUtils.getFragmentInHours(aDate, Calendar.DAY_OF_YEAR);
        assertEquals(expectedValue, testResult);
    }

}