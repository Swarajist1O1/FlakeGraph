class DummyClass_177218 {
    @Test
    public void empty_pooled() {
        final StreamDecoder decoder = newDecoder();
        final ByteBuf buf = ByteBufAllocator.DEFAULT.buffer();
        final HttpData data = decoder.decode(HttpData.wrap(buf));
        assertThat(buf.refCnt()).isZero();

        // Even for a pooled empty input, the result is unpooled since there's no point in pooling empty
        // buffers.
        assertThat(data.isPooled()).isFalse();
    }

}