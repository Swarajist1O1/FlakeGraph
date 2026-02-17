class DummyClass_156445 {
    @Test
    public void testMillisecondsOfSecondWithDate() {
        final long testResult = DateUtils.getFragmentInMilliseconds(aDate, Calendar.SECOND);
        assertEquals(millis, testResult);
    }

}