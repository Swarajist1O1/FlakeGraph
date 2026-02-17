class DummyClass_156468 {
    @Test
    public void testSecondsOfMonthWithCalendar() {
        final long testResult = DateUtils.getFragmentInSeconds(aCalendar, Calendar.MONTH);
        assertEquals(
                seconds
                        + ((minutes * DateUtils.MILLIS_PER_MINUTE)
                                + (hours * DateUtils.MILLIS_PER_HOUR) + ((days - 1) * DateUtils.MILLIS_PER_DAY))
                        / DateUtils.MILLIS_PER_SECOND,
                testResult);
    }

}