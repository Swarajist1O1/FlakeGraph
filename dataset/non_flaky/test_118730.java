class DummyClass_118730 {
    @Test
    public void testIsWritable() {
        EmptyByteBuf empty = new EmptyByteBuf(UnpooledByteBufAllocator.DEFAULT);
        assertFalse(empty.isWritable());
        assertFalse(empty.isWritable(1));
    }

}