class DummyClass_118718 {
    @Test
    public void testSoLinger() throws Exception {
        assertEquals(-1, socket.getSoLinger());
        socket.setSoLinger(10);
        assertEquals(10, socket.getSoLinger());
    }

}