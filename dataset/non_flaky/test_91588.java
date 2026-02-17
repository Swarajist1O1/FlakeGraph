class DummyClass_91588 {
    @Test
    public void testReadable() {
        String x = "\\x00\\x00\\x00\\x00\\x00\\x01\\xFC\\xA8";
        byte[] bytes = BytesUtil.fromReadableText(x);
        String y = BytesUtil.toHex(bytes);
        assertEquals(x, y);
    }

}