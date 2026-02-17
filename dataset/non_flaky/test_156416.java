class DummyClass_156416 {
    @Test
    public void hoursOutOfRange() {
        assertThrows(IllegalArgumentException.class, () -> new GmtTimeZone(false, 24, 0));
    }

}