class DummyClass_118711 {
    @Test(expected = DecoderException.class)
    public void testDecodeInvalidLine() {
        EmbeddedChannel channel = newChannel();
        assertTrue(channel.writeInbound(newBuffer("Ok\r\n")));
    }

}