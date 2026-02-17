class DummyClass_156466 {
    @Test
    public void testMillisecondsOfMonthWithCalendar() {
        final long testResult = DateUtils.getFragmentInMilliseconds(aCalendar, Calendar.MONTH);
        assertEquals(millis + (seconds * DateUtils.MILLIS_PER_SECOND) + (minutes * DateUtils.MILLIS_PER_MINUTE)
                + (hours * DateUtils.MILLIS_PER_HOUR) + ((days - 1) * DateUtils.MILLIS_PER_DAY),
testResult);
    }

}