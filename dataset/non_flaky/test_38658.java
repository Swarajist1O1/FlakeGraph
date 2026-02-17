class DummyClass_38658 {
    @Test
    public void testGetBytesOffsetZero() throws Exception {
        byte[] originalArray = {1, 2, 3};
        ByteBuffer wrapped = ByteBuffer.wrap(originalArray, 0, 2);
        assertEquals(0, wrapped.arrayOffset());
        assertEquals(2, wrapped.remaining());
        byte[] result = ByteBufferSchemaWrapper.getBytes(wrapped);
        assertNotSame(result, originalArray);
        assertArrayEquals(result, new byte[] {1,2});
    }

}