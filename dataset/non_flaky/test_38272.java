class DummyClass_38272 {
    @Test
    public void testBigValue() {
        byte[] bytes = new byte[64*1024];
        new Random().nextBytes(bytes);
        String encodeHexString = BaseEncoding.base16().lowerCase().encode(bytes);
        putDirect("row1", "col1", encodeHexString, 0);
        Pair<String, Long> pair = getDirect("row1", "col1", 1);
        Assert.assertEquals(0L, (long)pair.getRhSide());
        assertEquals(encodeHexString, pair.getLhSide());
    }

}