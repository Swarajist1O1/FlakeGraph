class DummyClass_156446 {
    @Test
    public void testMillisecondsOfSecondWithCalendar() {
        final long testResult = DateUtils.getFragmentInMilliseconds(aCalendar, Calendar.SECOND);
        assertEquals(millis, testResult);
        assertEquals(aCalendar.get(Calendar.MILLISECOND), testResult);
    }

}