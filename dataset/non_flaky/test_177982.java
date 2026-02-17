class DummyClass_177982 {
    @Test
    public void testIsRtl() {
        assertEquals(true, BidiFormatter.getInstance(true).isRtl(HE));
        assertEquals(true, BidiFormatter.getInstance(false).isRtl(HE));

        assertEquals(false, BidiFormatter.getInstance(true).isRtl(EN));
        assertEquals(false, BidiFormatter.getInstance(false).isRtl(EN));
    }

}