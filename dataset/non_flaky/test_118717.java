class DummyClass_118717 {
    @Test
    public void testSendBufferSize() throws Exception {
        int size = socket.getSendBufferSize();
        int newSize = 65535;
        assertTrue(size > 0);
        socket.setSendBufferSize(newSize);
        // Linux usually set it to double what is specified
        assertTrue(newSize <= socket.getSendBufferSize());
    }

}