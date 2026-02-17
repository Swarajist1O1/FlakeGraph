class DummyClass_118750 {
    @Test
    public void testWriteUsAsciiWrapped() {
        String usAscii = "NettyRocks";
        ByteBuf buf = unreleasableBuffer(Unpooled.buffer(16));
        assertWrapped(buf);
        buf.writeBytes(usAscii.getBytes(CharsetUtil.US_ASCII));
        ByteBuf buf2 = unreleasableBuffer(Unpooled.buffer(16));
        assertWrapped(buf2);
        ByteBufUtil.writeAscii(buf2, usAscii);

        assertEquals(buf, buf2);

        buf.unwrap().release();
        buf2.unwrap().release();
    }

}