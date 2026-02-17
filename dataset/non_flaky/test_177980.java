class DummyClass_177980 {
    @Test
    public void testIsRtlContext() {
        assertEquals(false, LTR_FMT.isRtlContext());
        assertEquals(true, RTL_FMT.isRtlContext());

        assertEquals(false, BidiFormatter.getInstance(Locale.ENGLISH).isRtlContext());
        assertEquals(true, BidiFormatter.getInstance(true).isRtlContext());
    }

}