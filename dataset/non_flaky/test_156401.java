class DummyClass_156401 {
    @Test
    public void test_getInstance_String() {
        final FastDateFormat format1 = FastDateFormat.getInstance("MM/DD/yyyy");
        final FastDateFormat format2 = FastDateFormat.getInstance("MM-DD-yyyy");
        final FastDateFormat format3 = FastDateFormat.getInstance("MM-DD-yyyy");

        assertNotSame(format1, format2);
        assertSame(format2, format3);
        assertEquals("MM/DD/yyyy", format1.getPattern());
        assertEquals(TimeZone.getDefault(), format1.getTimeZone());
        assertEquals(TimeZone.getDefault(), format2.getTimeZone());
    }

}