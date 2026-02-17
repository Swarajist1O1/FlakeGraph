class DummyClass_112664 {
    @Test
    public void canConvertToULong() {
        assertEquals(Long.valueOf(566517000), UNSIGNED_LONG_DECODER.apply("100001110001000101110100001000"));
        assertEquals(Long.valueOf(9577991), UNSIGNED_LONG_DECODER.apply("000000100100100010011000000111"));
    }

}