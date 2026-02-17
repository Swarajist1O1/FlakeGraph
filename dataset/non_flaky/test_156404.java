class DummyClass_156404 {
    @Test
    public void test_changeDefault_Locale_DateInstance() {
        final FastDateFormat format1 = FastDateFormat.getDateInstance(FastDateFormat.FULL, Locale.GERMANY);
        final FastDateFormat format2 = FastDateFormat.getDateInstance(FastDateFormat.FULL);
        Locale.setDefault(Locale.GERMANY);
        final FastDateFormat format3 = FastDateFormat.getDateInstance(FastDateFormat.FULL);

        assertSame(Locale.GERMANY, format1.getLocale());
        assertEquals(Locale.US, format2.getLocale());
        assertSame(Locale.GERMANY, format3.getLocale());
        assertNotSame(format1, format2);
        assertNotSame(format2, format3);
    }

}