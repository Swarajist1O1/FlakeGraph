class DummyClass_118727 {
    @Test
    public void testBackward() {
        final ByteBuf buf =
                Unpooled.copiedBuffer("abc\r\n\ndef\r\rghi\n\njkl\0\0mno  \t\tx", CharsetUtil.ISO_8859_1);
        final int length = buf.readableBytes();

        assertEquals(27, buf.forEachByteDesc(0, length, ByteProcessor.FIND_LINEAR_WHITESPACE));
        assertEquals(25, buf.forEachByteDesc(0, length, ByteProcessor.FIND_ASCII_SPACE));
        assertEquals(23, buf.forEachByteDesc(0, 28, ByteProcessor.FIND_NON_LINEAR_WHITESPACE));
        assertEquals(20, buf.forEachByteDesc(0, 24, ByteProcessor.FIND_NUL));
        assertEquals(18, buf.forEachByteDesc(0, 21, ByteProcessor.FIND_NON_NUL));
        assertEquals(15, buf.forEachByteDesc(0, 19, ByteProcessor.FIND_LF));
        assertEquals(13, buf.forEachByteDesc(0, 16, ByteProcessor.FIND_NON_LF));
        assertEquals(10, buf.forEachByteDesc(0, 14, ByteProcessor.FIND_CR));
        assertEquals(8,  buf.forEachByteDesc(0, 11, ByteProcessor.FIND_NON_CR));
        assertEquals(5,  buf.forEachByteDesc(0, 9, ByteProcessor.FIND_CRLF));
        assertEquals(2,  buf.forEachByteDesc(0, 6, ByteProcessor.FIND_NON_CRLF));
        assertEquals(-1, buf.forEachByteDesc(0, 3, ByteProcessor.FIND_CRLF));

        buf.release();
    }

}