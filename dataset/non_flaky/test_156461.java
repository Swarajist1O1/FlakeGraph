class DummyClass_156461 {
    @Test
    public void testMinutesOfDayWithDate() {
        long testResult = DateUtils.getFragmentInMinutes(aDate, Calendar.DATE);
        final long expectedValue = minutes + ((hours * DateUtils.MILLIS_PER_HOUR))/ DateUtils.MILLIS_PER_MINUTE;
        assertEquals(expectedValue, testResult);
        testResult = DateUtils.getFragmentInMinutes(aDate, Calendar.DAY_OF_YEAR);
        assertEquals(expectedValue, testResult);
    }

}