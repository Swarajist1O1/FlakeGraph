class DummyClass_156462 {
    @Test
    public void testMinutesOfDayWithCalendar() {
        long testResult = DateUtils.getFragmentInMinutes(aCalendar, Calendar.DATE);
        final long expectedValue = minutes + ((hours * DateUtils.MILLIS_PER_HOUR))/ DateUtils.MILLIS_PER_MINUTE;
        assertEquals(expectedValue, testResult);
        testResult = DateUtils.getFragmentInMinutes(aCalendar, Calendar.DAY_OF_YEAR);
        assertEquals(expectedValue, testResult);
    }

}