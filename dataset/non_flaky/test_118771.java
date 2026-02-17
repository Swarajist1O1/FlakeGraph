class DummyClass_118771 {
    @Test
    public void testIsTextWithAscii() {
        byte[] validBytes = {(byte) 0x00, (byte) 0x01, (byte) 0x37, (byte) 0x7F};
        byte[] invalidBytes = {(byte) 0x80, (byte) 0xFF};

        assertIsText(validBytes, true, CharsetUtil.US_ASCII);
        assertIsText(invalidBytes, false, CharsetUtil.US_ASCII);
    }

}