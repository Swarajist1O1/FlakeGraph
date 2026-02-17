class DummyClass_118709 {
    @Test(expected = DecoderException.class)
    public void testDecodeInvalidSeparator() {
        EmbeddedChannel channel = newChannel();
        assertTrue(channel.writeInbound(newBuffer("200:Ok\r\n")));
    }

}