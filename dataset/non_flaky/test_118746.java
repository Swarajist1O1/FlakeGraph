class DummyClass_118746 {
    @Test
    public void setShortBE() {
        int shortValue = 0x1234;

        ByteBuf buf = Unpooled.wrappedBuffer(new byte[2]).order(ByteOrder.BIG_ENDIAN);
        ByteBufUtil.setShortBE(buf, 0, shortValue);
        assertEquals(shortValue, buf.readShort());
        buf.resetReaderIndex();
        assertEquals(ByteBufUtil.swapShort((short) shortValue), buf.readShortLE());
        buf.release();

        buf = Unpooled.wrappedBuffer(new byte[2]).order(ByteOrder.LITTLE_ENDIAN);
        ByteBufUtil.setShortBE(buf, 0, shortValue);
        assertEquals((short) shortValue, buf.readShortLE());
        buf.resetReaderIndex();
        assertEquals(ByteBufUtil.swapShort((short) shortValue), buf.readShort());
        buf.release();
    }

}