class DummyClass_156427 {
    @Test
    public void testGetOffset() {
        assertEquals(-(6 * 60 + 30) * 60 * 1000,
            new GmtTimeZone(true, 6, 30).getOffset(1, 1, 1, 1, 1, 1));
    }

}