class DummyClass_156421 {
    @Test
    public void setRawOffset() {
        assertThrows(UnsupportedOperationException.class, () -> new GmtTimeZone(false, 0, 0).setRawOffset(0));
    }

}