class DummyClass_113993 {
    @Test
    public void testURLDecodeStringValidUtf8Middle() {

        String result = URLDecoderUtil.decode("xx%c3%aaxx", "UTF-8");
        assertEquals("xx\u00eaxx", result);
    }

}