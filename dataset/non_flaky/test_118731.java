class DummyClass_118731 {
    @Test
    public void testWriteEmptyByteBuf() {
        EmptyByteBuf empty = new EmptyByteBuf(UnpooledByteBufAllocator.DEFAULT);
        empty.writeBytes(Unpooled.EMPTY_BUFFER); // Ok
        ByteBuf nonEmpty = UnpooledByteBufAllocator.DEFAULT.buffer().writeBoolean(false);
        try {
            empty.writeBytes(nonEmpty);
            fail();
        } catch (IndexOutOfBoundsException ignored) {
            // Ignore.
        } finally {
            nonEmpty.release();
        }
    }

}