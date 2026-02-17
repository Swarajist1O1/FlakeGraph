class DummyClass_156420 {
    @Test
    public void getOffset() {
        assertEquals(0, new GmtTimeZone(false, 0, 0).getOffset(234304));
    }

}