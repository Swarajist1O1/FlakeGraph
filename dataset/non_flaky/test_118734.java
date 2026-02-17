class DummyClass_118734 {
    @Test
    public void testNioBuffer() {
        EmptyByteBuf empty = new EmptyByteBuf(UnpooledByteBufAllocator.DEFAULT);
        assertThat(empty.nioBufferCount(), is(1));
        assertThat(empty.nioBuffer().position(), is(0));
        assertThat(empty.nioBuffer().limit(), is(0));
        assertThat(empty.nioBuffer(), is(sameInstance(empty.nioBuffer())));
        assertThat(empty.nioBuffer(), is(sameInstance(empty.internalNioBuffer(empty.readerIndex(), 0))));
    }

}