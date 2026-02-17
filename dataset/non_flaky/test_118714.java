class DummyClass_118714 {
    @Test
    public void testKeepAlive() throws Exception {
        assertFalse(socket.isKeepAlive());
        socket.setKeepAlive(true);
        assertTrue(socket.isKeepAlive());
    }

}