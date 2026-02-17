class DummyClass_118702 {
    @Test(expected = EncoderException.class)
    public void testThrowsIfContentExpected() {
        EmbeddedChannel channel = new EmbeddedChannel(new SmtpRequestEncoder());
        try {
            assertTrue(channel.writeOutbound(SmtpRequests.data()));
            channel.writeOutbound(SmtpRequests.noop());
        } finally {
            channel.finishAndReleaseAll();
        }
    }

}