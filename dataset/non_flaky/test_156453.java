class DummyClass_156453 {
    @Test
    public void testSecondsofHourWithDate() {
        final long testResult = DateUtils.getFragmentInSeconds(aDate, Calendar.HOUR_OF_DAY);
        assertEquals(
                seconds
                        + (minutes
                                * DateUtils.MILLIS_PER_MINUTE / DateUtils.MILLIS_PER_SECOND),
                testResult);
    }

}