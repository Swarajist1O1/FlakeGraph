class DummyClass_118755 {
    @Test
    public void testWriteUtf8CompositeWrapped() {
        String utf8 = "Some UTF-8 like Ã¤ÃâÅÅ";
        ByteBuf buf = Unpooled.buffer(16);
        buf.writeBytes(utf8.getBytes(CharsetUtil.UTF_8));
        ByteBuf buf2 = new WrappedCompositeByteBuf(Unpooled.compositeBuffer().addComponent(
                Unpooled.buffer(8)).addComponent(Unpooled.buffer(24)));
        // write some byte so we start writing with an offset.
        buf2.writeByte(1);
        ByteBufUtil.writeUtf8(buf2, utf8);

        // Skip the previously written byte.
        assertEquals(buf, buf2.skipBytes(1));

        buf.release();
        buf2.release();
    }

}