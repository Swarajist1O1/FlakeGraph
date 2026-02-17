class DummyClass_91570 {
    @Test
    public void testIsSupportedDateFormat() {
        assertTrue(DateFormat.isSupportedDateFormat("2010-01-01"));
        assertTrue(DateFormat.isSupportedDateFormat("20100101"));
        assertTrue(DateFormat.isSupportedDateFormat("2010-01-01 01:01:01"));
        assertTrue(DateFormat.isSupportedDateFormat("2010-01-01 01:00:00.000"));

        assertFalse(DateFormat.isSupportedDateFormat("2010-01"));
        assertFalse(DateFormat.isSupportedDateFormat("2010/01/01"));
        assertFalse(DateFormat.isSupportedDateFormat("2010-1-1"));
        assertFalse(DateFormat.isSupportedDateFormat("abc"));
    }

}