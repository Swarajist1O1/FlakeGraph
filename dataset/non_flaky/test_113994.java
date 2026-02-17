class DummyClass_113994 {
    @Test
    public void testURLDecodeStringValidUtf8End() {

        String result = URLDecoderUtil.decode("xxxx%c3%aa", "UTF-8");
        assertEquals("xxxx\u00ea", result);
    }

}