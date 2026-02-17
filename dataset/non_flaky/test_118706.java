class DummyClass_118706 {
    @Test
    public void testDecodeOneLineResponseChunked() {
        EmbeddedChannel channel = newChannel();
        assertFalse(channel.writeInbound(newBuffer("200 Ok")));
        assertTrue(channel.writeInbound(newBuffer("\r\n")));
        assertTrue(channel.finish());

        SmtpResponse response = channel.readInbound();
        assertEquals(200, response.code());
        List<CharSequence> sequences = response.details();
        assertEquals(1, sequences.size());

        assertEquals("Ok", sequences.get(0).toString());
        assertNull(channel.readInbound());
    }

}