class DummyClass_118716 {
    @Test
    public void testReceivedBufferSize() throws Exception {
        int size = socket.getReceiveBufferSize();
        int newSize = 65535;
        assertTrue(size > 0);
        socket.setReceiveBufferSize(newSize);
        // Linux usually set it to double what is specified
        assertTrue(newSize <= socket.getReceiveBufferSize());
    }

}