class DummyClass_113992 {
    @Test
    public void testURLDecodeStringValidUtf8Start() {
        String result = URLDecoderUtil.decode("%c3%aaxxxx", "UTF-8");
        assertEquals("\u00eaxxxx", result);
    }

}