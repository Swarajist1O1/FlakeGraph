class DummyClass_118701 {
    @Test
    public void testEncodeDataAndContent() {
        EmbeddedChannel channel = new EmbeddedChannel(new SmtpRequestEncoder());
        assertTrue(channel.writeOutbound(SmtpRequests.data()));
        assertTrue(channel.writeOutbound(
                new DefaultSmtpContent(Unpooled.copiedBuffer("Subject: Test\r\n\r\n", CharsetUtil.US_ASCII))));
        assertTrue(channel.writeOutbound(
                new DefaultLastSmtpContent(Unpooled.copiedBuffer("Test\r\n", CharsetUtil.US_ASCII))));
        assertTrue(channel.finish());

        assertEquals("DATA\r\nSubject: Test\r\n\r\nTest\r\n.\r\n", getWrittenString(channel));
    }

}