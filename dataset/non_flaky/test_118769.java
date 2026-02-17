class DummyClass_118769 {
    @Test
    public void testIsTextWithUtf8() {
        byte[][] validUtf8Bytes = {
                "netty".getBytes(CharsetUtil.UTF_8),
                {(byte) 0x24},
                {(byte) 0xC2, (byte) 0xA2},
                {(byte) 0xE2, (byte) 0x82, (byte) 0xAC},
                {(byte) 0xF0, (byte) 0x90, (byte) 0x8D, (byte) 0x88},
                {(byte) 0x24,
                        (byte) 0xC2, (byte) 0xA2,
                        (byte) 0xE2, (byte) 0x82, (byte) 0xAC,
                        (byte) 0xF0, (byte) 0x90, (byte) 0x8D, (byte) 0x88} // multiple characters
        };
        for (byte[] bytes : validUtf8Bytes) {
            assertIsText(bytes, true, CharsetUtil.UTF_8);
        }
        byte[][] invalidUtf8Bytes = {
                {(byte) 0x80},
                {(byte) 0xF0, (byte) 0x82, (byte) 0x82, (byte) 0xAC}, // Overlong encodings
                {(byte) 0xC2},                                        // not enough bytes
                {(byte) 0xE2, (byte) 0x82},                           // not enough bytes
                {(byte) 0xF0, (byte) 0x90, (byte) 0x8D},              // not enough bytes
                {(byte) 0xC2, (byte) 0xC0},                           // not correct bytes
                {(byte) 0xE2, (byte) 0x82, (byte) 0xC0},              // not correct bytes
                {(byte) 0xF0, (byte) 0x90, (byte) 0x8D, (byte) 0xC0}, // not correct bytes
                {(byte) 0xC1, (byte) 0x80},                           // out of lower bound
                {(byte) 0xE0, (byte) 0x80, (byte) 0x80},              // out of lower bound
                {(byte) 0xED, (byte) 0xAF, (byte) 0x80}               // out of upper bound
        };
        for (byte[] bytes : invalidUtf8Bytes) {
            assertIsText(bytes, false, CharsetUtil.UTF_8);
        }
    }

}