class DummyClass_156470 {
    @Test
    public void testMinutesOfMonthWithCalendar() {
        final long testResult = DateUtils.getFragmentInMinutes(aCalendar, Calendar.MONTH);
        assertEquals( minutes  +((hours * DateUtils.MILLIS_PER_HOUR) + ((days - 1) * DateUtils.MILLIS_PER_DAY))
                        / DateUtils.MILLIS_PER_MINUTE,
                testResult);
    }

}