class DummyClass_177981 {
    @Test
    public void testBuilderIsRtlContext() {
        assertEquals(false, new BidiFormatter.Builder(false).build().isRtlContext());
        assertEquals(true, new BidiFormatter.Builder(true).build().isRtlContext());
    }

}