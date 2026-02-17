class DummyClass_156407 {
    @Test
    public void testCheckDefaults() {
        final FastDateFormat format = FastDateFormat.getInstance();
        final FastDateFormat medium = FastDateFormat.getDateTimeInstance(FastDateFormat.SHORT, FastDateFormat.SHORT);
        assertEquals(medium, format);

        final SimpleDateFormat sdf = new SimpleDateFormat();
        assertEquals(sdf.toPattern(), format.getPattern());

        assertEquals(Locale.getDefault(), format.getLocale());
        assertEquals(TimeZone.getDefault(), format.getTimeZone());
    }

}