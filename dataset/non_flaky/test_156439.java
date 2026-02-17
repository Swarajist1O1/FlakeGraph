class DummyClass_156439 {
    @Test
    public void testHourOfDayFragmentInLargerUnitWithDate() {
        assertEquals(0, DateUtils.getFragmentInHours(aDate, Calendar.HOUR_OF_DAY));
        assertEquals(0, DateUtils.getFragmentInDays(aDate, Calendar.HOUR_OF_DAY));
    }

}