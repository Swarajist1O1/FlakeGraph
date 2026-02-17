class DummyClass_156451 {
    @Test
    public void testMillisecondsOfHourWithDate() {
        final long testResult = DateUtils.getFragmentInMilliseconds(aDate, Calendar.HOUR_OF_DAY);
        assertEquals(millis + (seconds * DateUtils.MILLIS_PER_SECOND) + (minutes * DateUtils.MILLIS_PER_MINUTE), testResult);
    }

}