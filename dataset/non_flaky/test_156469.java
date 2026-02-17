class DummyClass_156469 {
    @Test
    public void testMinutesOfMonthWithDate() {
        final long testResult = DateUtils.getFragmentInMinutes(aDate, Calendar.MONTH);
        assertEquals(minutes
                                + ((hours * DateUtils.MILLIS_PER_HOUR) + ((days - 1) * DateUtils.MILLIS_PER_DAY))
                        / DateUtils.MILLIS_PER_MINUTE,
                testResult);
    }

}