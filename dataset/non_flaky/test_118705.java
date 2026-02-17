class DummyClass_118705 {
    @Test
    public void testDecodeOneLineResponseNoDetails() {
        EmbeddedChannel channel = newChannel();
        assertTrue(channel.writeInbound(newBuffer("250 \r\n")));
        assertTrue(channel.finish());

        SmtpResponse response = channel.readInbound();
        assertEquals(250, response.code());
        List<CharSequence> sequences = response.details();
        assertEquals(0, sequences.size());
    }

}