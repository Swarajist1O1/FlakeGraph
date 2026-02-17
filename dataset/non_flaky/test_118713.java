class DummyClass_118713 {
    @Test
    public void testUnPooledAllocatorIsBufferCopyNeededForWrite() {
        testIsBufferCopyNeededForWrite(UnpooledByteBufAllocator.DEFAULT);
    }

}