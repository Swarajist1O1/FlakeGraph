class DummyClass_118764 {
    @Test
    public void testWriteUsAsciiString() {
        AsciiString usAscii = new AsciiString("NettyRocks");
        ByteBuf buf = Unpooled.buffer(16);
        buf.writeBytes(usAscii.toString().getBytes(CharsetUtil.US_ASCII));
        ByteBuf buf2 = Unpooled.buffer(16);
        ByteBufUtil.writeAscii(buf2, usAscii);

        assertEquals(buf, buf2);

        buf.release();
        buf2.release();
    }

}