class DummyClass_156417 {
    @Test
    public void hoursInRange() {
        assertEquals(23 * 60 * 60 * 1000, new GmtTimeZone(false, 23, 0).getRawOffset());
    }

}