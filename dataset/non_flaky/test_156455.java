class DummyClass_156455 {
    @Test
    public void testMinutesOfHourWithDate() {
        final long testResult = DateUtils.getFragmentInMinutes(aDate, Calendar.HOUR_OF_DAY);
        assertEquals(minutes, testResult);
    }

}