class DummyClass_113990 {
    @Test
    public void testURLDecodeStringValidIso88591Middle() {

        String result = URLDecoderUtil.decode("xx%41xx", "ISO-8859-1");
        assertEquals("xxAxx", result);
    }

}