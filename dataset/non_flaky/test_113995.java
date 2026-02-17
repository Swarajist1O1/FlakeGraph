class DummyClass_113995 {
    @Test
    public void testURLDecodePlusCharAsSpace() {

        String result = URLDecoderUtil.decode("a+b", "UTF-8", true);
        assertEquals("a b", result);
    }

}