class DummyClass_156454 {
    @Test
    public void testSecondsofHourWithCalendar() {
        final long testResult = DateUtils.getFragmentInSeconds(aCalendar, Calendar.HOUR_OF_DAY);
        assertEquals(
                seconds
                        + (minutes
                                * DateUtils.MILLIS_PER_MINUTE / DateUtils.MILLIS_PER_SECOND),
                testResult);
    }

}