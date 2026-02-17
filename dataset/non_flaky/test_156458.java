class DummyClass_156458 {
    @Test
    public void testMillisecondsOfDayWithCalendar() {
        long testresult = DateUtils.getFragmentInMilliseconds(aCalendar, Calendar.DATE);
        final long expectedValue = millis + (seconds * DateUtils.MILLIS_PER_SECOND) + (minutes * DateUtils.MILLIS_PER_MINUTE) + (hours * DateUtils.MILLIS_PER_HOUR);
        assertEquals(expectedValue, testresult);
        testresult = DateUtils.getFragmentInMilliseconds(aCalendar, Calendar.DAY_OF_YEAR);
        assertEquals(expectedValue, testresult);
    }

}