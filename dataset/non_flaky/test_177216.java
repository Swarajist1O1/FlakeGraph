class DummyClass_177216 {
    @Test
    public void notEmpty() {
        final StreamDecoder decoder = newDecoder();
        final ByteBuf buf = ByteBufAllocator.DEFAULT.buffer();
        buf.writeBytes(PAYLOAD);
        final HttpData data = decoder.decode(HttpData.wrap(buf));
        assertThat(buf.refCnt()).isZero();
        assertThat(data.byteBuf().refCnt()).isOne();
        data.close();
    }

}