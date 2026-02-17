class DummyClass_38657 {
    @Test
    public void testGetBytesOffsetNonZero() throws Exception {
        byte[] originalArray = {1, 2, 3};
        ByteBuffer wrapped = ByteBuffer.wrap(originalArray);
        wrapped.position(1);
        assertEquals(1, wrapped.position());
        wrapped = wrapped.slice();
        assertEquals(1, wrapped.arrayOffset());
        assertEquals(2, wrapped.remaining());
        byte[] result = ByteBufferSchemaWrapper.getBytes(wrapped);
        assertNotSame(result, originalArray);
        assertArrayEquals(result, new byte[] {2,3});
    }

}