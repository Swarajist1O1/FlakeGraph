class DummyClass_118708 {
    @Test
    public void testDecodeTwoLineResponseChunked() {
        EmbeddedChannel channel = newChannel();
        assertFalse(channel.writeInbound(newBuffer("200-")));
        assertFalse(channel.writeInbound(newBuffer("Hello\r\n2")));
        assertFalse(channel.writeInbound(newBuffer("00 Ok")));
        assertTrue(channel.writeInbound(newBuffer("\r\n")));
        assertTrue(channel.finish());

        SmtpResponse response = channel.readInbound();
        assertEquals(200, response.code());
        List<CharSequence> sequences = response.details();
        assertEquals(2, sequences.size());

        assertEquals("Hello", sequences.get(0).toString());
        assertEquals("Ok", sequences.get(1).toString());
        assertNull(channel.readInbound());
    }

}