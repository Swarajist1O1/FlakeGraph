class DummyClass_156474 {
    @Test
    public void testMillisecondsOfYearWithCalendar() {
        final long testResult = DateUtils.getFragmentInMilliseconds(aCalendar, Calendar.YEAR);
        assertEquals(millis + (seconds * DateUtils.MILLIS_PER_SECOND) + (minutes * DateUtils.MILLIS_PER_MINUTE)
                + (hours * DateUtils.MILLIS_PER_HOUR) + ((aCalendar.get(Calendar.DAY_OF_YEAR) - 1) * DateUtils.MILLIS_PER_DAY),
testResult);
    }

}