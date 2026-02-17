class DummyClass_118710 {
    @Test(expected = DecoderException.class)
    public void testDecodeInvalidCode() {
        EmbeddedChannel channel = newChannel();
        assertTrue(channel.writeInbound(newBuffer("xyz Ok\r\n")));
    }

}