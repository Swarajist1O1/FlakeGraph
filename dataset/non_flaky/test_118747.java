class DummyClass_118747 {
    @Test
    public void writeMediumBE() {
        int mediumValue = 0x123456;

        ByteBuf buf = Unpooled.buffer(4).order(ByteOrder.BIG_ENDIAN);
        ByteBufUtil.writeMediumBE(buf, mediumValue);
        assertEquals(mediumValue, buf.readMedium());
        buf.resetReaderIndex();
        assertEquals(ByteBufUtil.swapMedium(mediumValue), buf.readMediumLE());
        buf.release();

        buf = Unpooled.buffer(4).order(ByteOrder.LITTLE_ENDIAN);
        ByteBufUtil.writeMediumBE(buf, mediumValue);
        assertEquals(mediumValue, buf.readMediumLE());
        buf.resetReaderIndex();
        assertEquals(ByteBufUtil.swapMedium(mediumValue), buf.readMedium());
        buf.release();
    }

}