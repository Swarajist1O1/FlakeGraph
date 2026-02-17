class DummyClass_118735 {
    @Test
    public void testMemoryAddress() {
        EmptyByteBuf empty = new EmptyByteBuf(UnpooledByteBufAllocator.DEFAULT);
        if (empty.hasMemoryAddress()) {
            assertThat(empty.memoryAddress(), is(not(0L)));
        } else {
            try {
                empty.memoryAddress();
                fail();
            } catch (UnsupportedOperationException ignored) {
                // Ignore.
            }
        }
    }

}