class DummyClass_156473 {
    @Test
    public void testMillisecondsOfYearWithDate() {
        final long testResult = DateUtils.getFragmentInMilliseconds(aDate, Calendar.YEAR);
        final Calendar cal = Calendar.getInstance();
        cal.setTime(aDate);
        assertEquals(millis + (seconds * DateUtils.MILLIS_PER_SECOND) + (minutes * DateUtils.MILLIS_PER_MINUTE)
                                + (hours * DateUtils.MILLIS_PER_HOUR) + ((cal.get(Calendar.DAY_OF_YEAR) - 1)* DateUtils.MILLIS_PER_DAY),
                testResult);
    }

}