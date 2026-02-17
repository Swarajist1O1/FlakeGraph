class DummyClass_113989 {
    @Test
    public void testURLDecodeStringValidIso88591Start() {

        String result = URLDecoderUtil.decode("%41xxxx", "ISO-8859-1");
        assertEquals("Axxxx", result);
    }

}