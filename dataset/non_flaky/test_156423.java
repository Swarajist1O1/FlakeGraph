class DummyClass_156423 {
    @Test
    public void getID() {
        assertEquals("GMT+00:00", new GmtTimeZone(false, 0, 0).getID());
        assertEquals("GMT+01:02", new GmtTimeZone(false, 1, 2).getID());
        assertEquals("GMT+11:22", new GmtTimeZone(false, 11, 22).getID());
        assertEquals("GMT-01:02", new GmtTimeZone(true, 1, 2).getID());
        assertEquals("GMT-11:22", new GmtTimeZone(true, 11, 22).getID());
    }

}