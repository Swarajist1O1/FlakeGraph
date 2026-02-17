class DummyClass_156419 {
    @Test
    public void minutesInRange() {
        assertEquals(59 * 60 * 1000, new GmtTimeZone(false, 0, 59).getRawOffset());
    }

}