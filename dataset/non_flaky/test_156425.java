class DummyClass_156425 {
    @Test
    public void inDaylightTime() {
        assertFalse(new GmtTimeZone(false, 0, 0).useDaylightTime());
    }

}