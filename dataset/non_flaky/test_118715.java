class DummyClass_118715 {
    @Test
    public void testTcpNoDelay() throws Exception {
        assertFalse(socket.isTcpNoDelay());
        socket.setTcpNoDelay(true);
        assertTrue(socket.isTcpNoDelay());
    }

}