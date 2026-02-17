class DummyClass_156406 {
    @Test
    public void test_getInstance_String_TimeZone_Locale() {
        final FastDateFormat format1 = FastDateFormat.getInstance("MM/DD/yyyy",
                TimeZone.getTimeZone("Atlantic/Reykjavik"), Locale.GERMANY);
        final FastDateFormat format2 = FastDateFormat.getInstance("MM/DD/yyyy", Locale.GERMANY);
        final FastDateFormat format3 = FastDateFormat.getInstance("MM/DD/yyyy",
                TimeZone.getDefault(), Locale.GERMANY);

        assertNotSame(format1, format2);
        assertEquals(TimeZone.getTimeZone("Atlantic/Reykjavik"), format1.getTimeZone());
        assertEquals(TimeZone.getDefault(), format2.getTimeZone());
        assertEquals(TimeZone.getDefault(), format3.getTimeZone());
        assertEquals(Locale.GERMANY, format1.getLocale());
        assertEquals(Locale.GERMANY, format2.getLocale());
        assertEquals(Locale.GERMANY, format3.getLocale());
    }

}