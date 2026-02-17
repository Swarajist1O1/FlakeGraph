class DummyClass_156456 {
    @Test
    public void testMinutesOfHourWithCalendar() {
        final long testResult = DateUtils.getFragmentInMinutes(aCalendar, Calendar.HOUR_OF_DAY);
        assertEquals(minutes, testResult);
    }

}