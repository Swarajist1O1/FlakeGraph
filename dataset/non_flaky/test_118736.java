class DummyClass_118736 {
    @Test
    public void consistentEqualsAndHashCodeWithAbstractBytebuf() {
        ByteBuf empty = new EmptyByteBuf(UnpooledByteBufAllocator.DEFAULT);
        ByteBuf emptyAbstract = new UnpooledHeapByteBuf(UnpooledByteBufAllocator.DEFAULT, 0, 0);
        assertEquals(emptyAbstract, empty);
        assertEquals(emptyAbstract.hashCode(), empty.hashCode());
        assertEquals(EmptyByteBuf.EMPTY_BYTE_BUF_HASH_CODE, empty.hashCode());
        assertTrue(emptyAbstract.release());
        assertFalse(empty.release());
    }

}