class DummyClass_118748 {
    @Test
    public void testWriteUsAscii() {
        String usAscii = "NettyRocks";
        ByteBuf buf = Unpooled.buffer(16);
        buf.writeBytes(usAscii.getBytes(CharsetUtil.US_ASCII));
        ByteBuf buf2 = Unpooled.buffer(16);
        ByteBufUtil.writeAscii(buf2, usAscii);

        assertEquals(buf, buf2);

        buf.release();
        buf2.release();
    }

}