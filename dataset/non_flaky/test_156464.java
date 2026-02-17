class DummyClass_156464 {
    @Test
    public void testHoursOfDayWithCalendar() {
        long testResult = DateUtils.getFragmentInHours(aCalendar, Calendar.DATE);
        final long expectedValue = hours;
        assertEquals(expectedValue, testResult);
        testResult = DateUtils.getFragmentInHours(aCalendar, Calendar.DAY_OF_YEAR);
        assertEquals(expectedValue, testResult);
    }

}