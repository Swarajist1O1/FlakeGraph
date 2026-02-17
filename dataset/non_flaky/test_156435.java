class DummyClass_156435 {
    @Test
    public void testSecondFragmentInLargerUnitWithDate() {
        assertEquals(0, DateUtils.getFragmentInSeconds(aDate, Calendar.SECOND));
        assertEquals(0, DateUtils.getFragmentInMinutes(aDate, Calendar.SECOND));
        assertEquals(0, DateUtils.getFragmentInHours(aDate, Calendar.SECOND));
        assertEquals(0, DateUtils.getFragmentInDays(aDate, Calendar.SECOND));
    }

}