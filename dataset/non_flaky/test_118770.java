class DummyClass_118770 {
    @Test
    public void testIsTextWithoutOptimization() {
        byte[] validBytes = {(byte) 0x01, (byte) 0xD8, (byte) 0x37, (byte) 0xDC};
        byte[] invalidBytes = {(byte) 0x01, (byte) 0xD8};

        assertIsText(validBytes, true, CharsetUtil.UTF_16LE);
        assertIsText(invalidBytes, false, CharsetUtil.UTF_16LE);
    }

}