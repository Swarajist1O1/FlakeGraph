class DummyClass_156457 {
    @Test
    public void testMillisecondsOfDayWithDate() {
        long testresult = DateUtils.getFragmentInMilliseconds(aDate, Calendar.DATE);
        final long expectedValue = millis + (seconds * DateUtils.MILLIS_PER_SECOND) + (minutes * DateUtils.MILLIS_PER_MINUTE) + (hours * DateUtils.MILLIS_PER_HOUR);
        assertEquals(expectedValue, testresult);
        testresult = DateUtils.getFragmentInMilliseconds(aDate, Calendar.DAY_OF_YEAR);
        assertEquals(expectedValue, testresult);
    }

}