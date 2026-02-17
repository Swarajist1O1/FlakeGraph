class DummyClass_118707 {
    @Test
    public void testDecodeTwoLineResponse() {
        EmbeddedChannel channel = newChannel();
        assertTrue(channel.writeInbound(newBuffer("200-Hello\r\n200 Ok\r\n")));
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