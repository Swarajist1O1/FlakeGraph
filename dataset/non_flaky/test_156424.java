class DummyClass_156424 {
    @Test
    public void useDaylightTime() {
        assertFalse(new GmtTimeZone(false, 0, 0).useDaylightTime());
    }

}