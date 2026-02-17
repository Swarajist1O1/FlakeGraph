class DummyClass_156448 {
    @Test
    public void testMillisecondsOfMinuteWithCalender() {
        final long testResult = DateUtils.getFragmentInMilliseconds(aCalendar, Calendar.MINUTE);
        assertEquals(millis + (seconds * DateUtils.MILLIS_PER_SECOND), testResult);
    }

}