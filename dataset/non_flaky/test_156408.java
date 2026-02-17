class DummyClass_156408 {
    @Test
    public void testCheckDifferingStyles() {
        final FastDateFormat shortShort = FastDateFormat.getDateTimeInstance(FastDateFormat.SHORT, FastDateFormat.SHORT, Locale.US);
        final FastDateFormat shortLong = FastDateFormat.getDateTimeInstance(FastDateFormat.SHORT, FastDateFormat.LONG, Locale.US);
        final FastDateFormat longShort = FastDateFormat.getDateTimeInstance(FastDateFormat.LONG, FastDateFormat.SHORT, Locale.US);
        final FastDateFormat longLong = FastDateFormat.getDateTimeInstance(FastDateFormat.LONG, FastDateFormat.LONG, Locale.US);

        assertNotEquals(shortShort, shortLong);
        assertNotEquals(shortShort, longShort);
        assertNotEquals(shortShort, longLong);
        assertNotEquals(shortLong, longShort);
        assertNotEquals(shortLong, longLong);
        assertNotEquals(longShort, longLong);
    }

}