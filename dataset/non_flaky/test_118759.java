class DummyClass_118759 {
    @Test
    public void testWriteUtf8InvalidSurrogatesSwitched() {
        String surrogateString = new StringBuilder(2)
                                .append('a')
                                .append('\uDC00')
                                .append('\uD800')
                                .append('b')
                                .toString();
        ByteBuf buf = Unpooled.buffer(16);
        buf.writeBytes(surrogateString.getBytes(CharsetUtil.UTF_8));
        ByteBuf buf2 = Unpooled.buffer(16);
        ByteBufUtil.writeUtf8(buf2, surrogateString);

        assertEquals(buf, buf2);
        assertEquals(buf.readableBytes(), ByteBufUtil.utf8Bytes(surrogateString));

        buf.release();
        buf2.release();
    }

}