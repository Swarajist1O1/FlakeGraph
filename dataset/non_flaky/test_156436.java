class DummyClass_156436 {
    @Test
    public void testSecondFragmentInLargerUnitWithCalendar() {
        assertEquals(0, DateUtils.getFragmentInSeconds(aCalendar, Calendar.SECOND));
        assertEquals(0, DateUtils.getFragmentInMinutes(aCalendar, Calendar.SECOND));
        assertEquals(0, DateUtils.getFragmentInHours(aCalendar, Calendar.SECOND));
        assertEquals(0, DateUtils.getFragmentInDays(aCalendar, Calendar.SECOND));
    }

}