class DummyClass_156418 {
    @Test
    public void minutesOutOfRange() {
        assertThrows(IllegalArgumentException.class, () -> new GmtTimeZone(false, 0, 60));
    }

}