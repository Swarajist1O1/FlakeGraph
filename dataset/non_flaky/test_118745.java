class DummyClass_118745 {
    @Test
    public void writeShortBE() {
        int expected = 0x1234;

        ByteBuf buf = Unpooled.buffer(2).order(ByteOrder.BIG_ENDIAN);
        ByteBufUtil.writeShortBE(buf, expected);
        assertEquals(expected, buf.readShort());
        buf.resetReaderIndex();
        assertEquals(ByteBufUtil.swapShort((short) expected), buf.readShortLE());
        buf.release();

        buf = Unpooled.buffer(2).order(ByteOrder.LITTLE_ENDIAN);
        ByteBufUtil.writeShortBE(buf, expected);
        assertEquals((short) expected, buf.readShortLE());
        buf.resetReaderIndex();
        assertEquals(ByteBufUtil.swapShort((short) expected), buf.readShort());
        buf.release();
    }

}