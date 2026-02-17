class DummyClass_156472 {
    @Test
    public void testHoursOfMonthWithCalendar() {
        final long testResult = DateUtils.getFragmentInHours(aCalendar, Calendar.MONTH);
        assertEquals( hours +(((days - 1) * DateUtils.MILLIS_PER_DAY))
                        / DateUtils.MILLIS_PER_HOUR,
                testResult);
    }

}