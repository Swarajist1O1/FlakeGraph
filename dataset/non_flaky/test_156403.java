class DummyClass_156403 {
    @Test
    public void test_getInstance_String_Locale() {
        final FastDateFormat format1 = FastDateFormat.getInstance("MM/DD/yyyy", Locale.GERMANY);
        final FastDateFormat format2 = FastDateFormat.getInstance("MM/DD/yyyy");
        final FastDateFormat format3 = FastDateFormat.getInstance("MM/DD/yyyy", Locale.GERMANY);

        assertNotSame(format1, format2);
        assertSame(format1, format3);
        assertEquals(Locale.GERMANY, format1.getLocale());
    }

}