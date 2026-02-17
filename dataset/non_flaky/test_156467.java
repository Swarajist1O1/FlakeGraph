class DummyClass_156467 {
    @Test
    public void testSecondsOfMonthWithDate() {
        final long testResult = DateUtils.getFragmentInSeconds(aDate, Calendar.MONTH);
        assertEquals(
                seconds
                        + ((minutes * DateUtils.MILLIS_PER_MINUTE)
                                + (hours * DateUtils.MILLIS_PER_HOUR) + ((days - 1) * DateUtils.MILLIS_PER_DAY))
                        / DateUtils.MILLIS_PER_SECOND,
                testResult);
    }

}