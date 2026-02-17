class DummyClass_156452 {
    @Test
    public void testMillisecondsOfHourWithCalendar() {
        final long testResult = DateUtils.getFragmentInMilliseconds(aCalendar, Calendar.HOUR_OF_DAY);
        assertEquals(millis + (seconds * DateUtils.MILLIS_PER_SECOND) + (minutes * DateUtils.MILLIS_PER_MINUTE), testResult);
    }

}