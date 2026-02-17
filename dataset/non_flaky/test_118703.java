class DummyClass_118703 {
    @Test
    public void testRsetClearsContentExpectedFlag() {
        EmbeddedChannel channel = new EmbeddedChannel(new SmtpRequestEncoder());

        assertTrue(channel.writeOutbound(SmtpRequests.data()));
        assertTrue(channel.writeOutbound(SmtpRequests.rset()));
        assertTrue(channel.writeOutbound(SmtpRequests.noop()));
        assertTrue(channel.finish());

        assertEquals("DATA\r\nRSET\r\nNOOP\r\n", getWrittenString(channel));
    }

}