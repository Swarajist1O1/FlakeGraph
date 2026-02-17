class DummyClass_118753 {
    @Test
    public void testWriteUtf8() {
        String usAscii = "Some UTF-8 like Ã¤ÃâÅÅ";
        ByteBuf buf = Unpooled.buffer(16);
        buf.writeBytes(usAscii.getBytes(CharsetUtil.UTF_8));
        ByteBuf buf2 = Unpooled.buffer(16);
        ByteBufUtil.writeUtf8(buf2, usAscii);

        assertEquals(buf, buf2);

        buf.release();
        buf2.release();
    }

}