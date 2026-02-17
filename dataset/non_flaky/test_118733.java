class DummyClass_118733 {
    @Test
    public void testArray() {
        EmptyByteBuf empty = new EmptyByteBuf(UnpooledByteBufAllocator.DEFAULT);
        assertThat(empty.hasArray(), is(true));
        assertThat(empty.array().length, is(0));
        assertThat(empty.arrayOffset(), is(0));
    }

}