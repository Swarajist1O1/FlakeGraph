class DummyClass_118749 {
    @Test
    public void testWriteUsAsciiSwapped() {
        String usAscii = "NettyRocks";
        ByteBuf buf = Unpooled.buffer(16);
        buf.writeBytes(usAscii.getBytes(CharsetUtil.US_ASCII));
        SwappedByteBuf buf2 = new SwappedByteBuf(Unpooled.buffer(16));
        ByteBufUtil.writeAscii(buf2, usAscii);

        assertEquals(buf, buf2);

        buf.release();
        buf2.release();
    }

}