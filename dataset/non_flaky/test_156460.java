class DummyClass_156460 {
    @Test
    public void testSecondsOfDayWithCalendar() {
        long testresult = DateUtils.getFragmentInSeconds(aCalendar, Calendar.DATE);
        final long expectedValue = seconds + ((minutes * DateUtils.MILLIS_PER_MINUTE) + (hours * DateUtils.MILLIS_PER_HOUR))/ DateUtils.MILLIS_PER_SECOND;
        assertEquals(expectedValue, testresult);
        testresult = DateUtils.getFragmentInSeconds(aCalendar, Calendar.DAY_OF_YEAR);
        assertEquals(expectedValue, testresult);
    }

}