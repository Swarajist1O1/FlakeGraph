class DummyClass_156447 {
    @Test
    public void testMillisecondsOfMinuteWithDate() {
        final long testResult = DateUtils.getFragmentInMilliseconds(aDate, Calendar.MINUTE);
        assertEquals(millis + (seconds * DateUtils.MILLIS_PER_SECOND), testResult);
    }

}