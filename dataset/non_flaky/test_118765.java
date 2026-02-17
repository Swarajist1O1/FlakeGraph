class DummyClass_118765 {
    @Test
    public void testWriteUtf8Wrapped() {
        String usAscii = "Some UTF-8 like Ã¤ÃâÅÅ";
        ByteBuf buf = unreleasableBuffer(Unpooled.buffer(16));
        assertWrapped(buf);
        buf.writeBytes(usAscii.getBytes(CharsetUtil.UTF_8));
        ByteBuf buf2 = unreleasableBuffer(Unpooled.buffer(16));
        assertWrapped(buf2);
        ByteBufUtil.writeUtf8(buf2, usAscii);

        assertEquals(buf, buf2);

        buf.release();
        buf2.release();
    }

}