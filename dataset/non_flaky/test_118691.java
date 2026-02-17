class DummyClass_118691 {
    @Test
    public void testEncodeEhlo() {
        testEncode(SmtpRequests.ehlo("localhost"), "EHLO localhost\r\n");
    }

}