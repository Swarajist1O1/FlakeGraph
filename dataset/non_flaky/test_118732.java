class DummyClass_118732 {
    @Test
    public void testIsReadable() {
        EmptyByteBuf empty = new EmptyByteBuf(UnpooledByteBufAllocator.DEFAULT);
        assertFalse(empty.isReadable());
        assertFalse(empty.isReadable(1));
    }

}