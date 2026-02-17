class DummyClass_38655 {
    @Test
    public void testGetBytesNoCopy() throws Exception {
        byte[] originalArray = {1, 2, 3};
        ByteBuffer wrapped = ByteBuffer.wrap(originalArray);
        assertEquals(0, wrapped.arrayOffset());
        assertEquals(3, wrapped.remaining());
        assertSame(ByteBufferSchemaWrapper.getBytes(wrapped), originalArray);
    }

}