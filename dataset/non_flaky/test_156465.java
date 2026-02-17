class DummyClass_156465 {
    @Test
    public void testMillisecondsOfMonthWithDate() {
        final long testResult = DateUtils.getFragmentInMilliseconds(aDate, Calendar.MONTH);
        assertEquals(millis + (seconds * DateUtils.MILLIS_PER_SECOND) + (minutes * DateUtils.MILLIS_PER_MINUTE)
                                + (hours * DateUtils.MILLIS_PER_HOUR) + ((days - 1) * DateUtils.MILLIS_PER_DAY),
                testResult);
    }

}