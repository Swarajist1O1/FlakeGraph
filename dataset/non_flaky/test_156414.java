class DummyClass_156414 {
    @Test
    public void testLANG_1152() {
        final TimeZone utc = FastTimeZone.getGmtTimeZone();
        final Date date = new Date(Long.MAX_VALUE);

        String dateAsString = FastDateFormat.getInstance("yyyy-MM-dd", utc, Locale.US).format(date);
        assertEquals("292278994-08-17", dateAsString);

        dateAsString = FastDateFormat.getInstance("dd/MM/yyyy", utc, Locale.US).format(date);
        assertEquals("17/08/292278994", dateAsString);
    }

}