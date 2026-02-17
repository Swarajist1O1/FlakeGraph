class DummyClass_156409 {
    @Test
    public void testDateDefaults() {
        assertEquals(FastDateFormat.getDateInstance(FastDateFormat.LONG, Locale.CANADA),
                FastDateFormat.getDateInstance(FastDateFormat.LONG, TimeZone.getDefault(), Locale.CANADA));

        assertEquals(FastDateFormat.getDateInstance(FastDateFormat.LONG, TimeZone.getTimeZone("America/New_York")),
                FastDateFormat.getDateInstance(FastDateFormat.LONG, TimeZone.getTimeZone("America/New_York"), Locale.getDefault()));

        assertEquals(FastDateFormat.getDateInstance(FastDateFormat.LONG),
                FastDateFormat.getDateInstance(FastDateFormat.LONG, TimeZone.getDefault(), Locale.getDefault()));
    }

}