class DummyClass_118704 {
    @Test
    public void testDecodeOneLineResponse() {
        EmbeddedChannel channel = newChannel();
        assertTrue(channel.writeInbound(newBuffer("200 Ok\r\n")));
        assertTrue(channel.finish());

        SmtpResponse response = channel.readInbound();
        assertEquals(200, response.code());
        List<CharSequence> sequences = response.details();
        assertEquals(1, sequences.size());

        assertEquals("Ok", sequences.get(0).toString());
        assertNull(channel.readInbound());
    }

}