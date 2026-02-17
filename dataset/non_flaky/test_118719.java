class DummyClass_118719 {
    @Test
    public void testDoubleCloseDoesNotThrow() throws IOException {
        Socket socket = Socket.newSocketStream();
        socket.close();
        socket.close();
    }

}