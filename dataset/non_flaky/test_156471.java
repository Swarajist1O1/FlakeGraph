class DummyClass_156471 {
    @Test
    public void testHoursOfMonthWithDate() {
        final long testResult = DateUtils.getFragmentInHours(aDate, Calendar.MONTH);
        assertEquals(hours + (((days - 1) * DateUtils.MILLIS_PER_DAY))
                        / DateUtils.MILLIS_PER_HOUR,
                testResult);
    }

}