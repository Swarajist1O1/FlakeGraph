class DummyClass_113991 {
    @Test
    public void testURLDecodeStringValidIso88591End() {

        String result = URLDecoderUtil.decode("xxxx%41", "ISO-8859-1");
        assertEquals("xxxxA", result);
    }

}