class DummyClass_118720 {
    @Test
    public void testTrafficClass() throws IOException {
        // IPTOS_THROUGHPUT
        final int value = 0x08;
        socket.setTrafficClass(value);
        assertEquals(value, socket.getTrafficClass());
    }

}