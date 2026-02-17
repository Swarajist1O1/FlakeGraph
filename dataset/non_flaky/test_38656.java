class DummyClass_38656 {
    @Test
    public void testGetBytesOffsetZeroDifferentLen() throws Exception {
        byte[] originalArray = {1, 2, 3};
        ByteBuffer wrapped = ByteBuffer.wrap(originalArray, 1, 2);
        assertEquals(0, wrapped.arrayOffset());
        assertEquals(2, wrapped.remaining());
        byte[] result = ByteBufferSchemaWrapper.getBytes(wrapped);
        assertNotSame(result, originalArray);
        assertArrayEquals(result, new byte[] {2,3});
    }

}