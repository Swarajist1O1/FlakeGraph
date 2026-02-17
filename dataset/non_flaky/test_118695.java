class DummyClass_118695 {
    @Test
    public void testEncodeRcpt() {
        testEncode(SmtpRequests.rcpt("me@netty.io"), "RCPT TO:<me@netty.io>\r\n");
    }

}