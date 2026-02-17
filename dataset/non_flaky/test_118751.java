class DummyClass_118751 {
    @Test
    public void testWriteUsAsciiComposite() {
        String usAscii = "NettyRocks";
        ByteBuf buf = Unpooled.buffer(16);
        buf.writeBytes(usAscii.getBytes(CharsetUtil.US_ASCII));
        ByteBuf buf2 = Unpooled.compositeBuffer().addComponent(
                Unpooled.buffer(8)).addComponent(Unpooled.buffer(24));
        // write some byte so we start writing with an offset.
        buf2.writeByte(1);
        ByteBufUtil.writeAscii(buf2, usAscii);

        // Skip the previously written byte.
        assertEquals(buf, buf2.skipBytes(1));

        buf.release();
        buf2.release();
    }

}