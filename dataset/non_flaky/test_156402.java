class DummyClass_156402 {
    @Test
    public void test_getInstance_String_TimeZone() {

        final FastDateFormat format1 = FastDateFormat.getInstance("MM/DD/yyyy",
                TimeZone.getTimeZone("Atlantic/Reykjavik"));
        final FastDateFormat format2 = FastDateFormat.getInstance("MM/DD/yyyy");
        final FastDateFormat format3 = FastDateFormat.getInstance("MM/DD/yyyy", TimeZone.getDefault());
        final FastDateFormat format4 = FastDateFormat.getInstance("MM/DD/yyyy", TimeZone.getDefault());
        final FastDateFormat format5 = FastDateFormat.getInstance("MM-DD-yyyy", TimeZone.getDefault());
        final FastDateFormat format6 = FastDateFormat.getInstance("MM-DD-yyyy");

        assertNotSame(format1, format2);
        assertEquals(TimeZone.getTimeZone("Atlantic/Reykjavik"), format1.getTimeZone());
        assertEquals(TimeZone.getDefault(), format2.getTimeZone());
        assertSame(format3, format4);
        assertNotSame(format3, format5);
        assertNotSame(format4, format6);
    }

}