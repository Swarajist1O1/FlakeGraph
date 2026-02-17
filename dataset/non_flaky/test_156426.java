class DummyClass_156426 {
    @Test
    public void testToString() {
        assertEquals("[GmtTimeZone id=\"GMT-12:00\",offset=-43200000]",
            new GmtTimeZone(true, 12, 0).toString());
    }

}