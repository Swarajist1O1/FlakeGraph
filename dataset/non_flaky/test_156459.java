class DummyClass_156459 {
    @Test
    public void testSecondsOfDayWithDate() {
        long testresult = DateUtils.getFragmentInSeconds(aDate, Calendar.DATE);
        final long expectedValue = seconds + ((minutes * DateUtils.MILLIS_PER_MINUTE) + (hours * DateUtils.MILLIS_PER_HOUR))/ DateUtils.MILLIS_PER_SECOND;
        assertEquals(expectedValue, testresult);
        testresult = DateUtils.getFragmentInSeconds(aDate, Calendar.DAY_OF_YEAR);
        assertEquals(expectedValue, testresult);
    }

}