class DummyClass_156450 {
    @Test
    public void testSecondsofMinuteWithCalendar() {
        final long testResult = DateUtils.getFragmentInSeconds(aCalendar, Calendar.MINUTE);
        assertEquals(seconds, testResult);
        assertEquals(aCalendar.get(Calendar.SECOND), testResult);
    }

}