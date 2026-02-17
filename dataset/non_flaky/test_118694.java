class DummyClass_118694 {
    @Test
    public void testEncodeMailNullSender() {
        testEncode(SmtpRequests.mail(null), "MAIL FROM:<>\r\n");
    }

}