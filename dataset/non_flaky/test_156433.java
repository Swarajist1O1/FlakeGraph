class DummyClass_156433 {
    @Test
    public void testMillisecondFragmentInLargerUnitWithDate() {
        assertEquals(0, DateUtils.getFragmentInMilliseconds(aDate, Calendar.MILLISECOND));
        assertEquals(0, DateUtils.getFragmentInSeconds(aDate, Calendar.MILLISECOND));
        assertEquals(0, DateUtils.getFragmentInMinutes(aDate, Calendar.MILLISECOND));
        assertEquals(0, DateUtils.getFragmentInHours(aDate, Calendar.MILLISECOND));
        assertEquals(0, DateUtils.getFragmentInDays(aDate, Calendar.MILLISECOND));
    }

}