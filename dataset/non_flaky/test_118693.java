class DummyClass_118693 {
    @Test
    public void testEncodeMail() {
        testEncode(SmtpRequests.mail("me@netty.io"), "MAIL FROM:<me@netty.io>\r\n");
    }

}