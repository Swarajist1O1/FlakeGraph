class DummyClass_156449 {
    @Test
    public void testSecondsofMinuteWithDate() {
        final long testResult = DateUtils.getFragmentInSeconds(aDate, Calendar.MINUTE);
        assertEquals(seconds, testResult);
    }

}