class DummyClass_118692 {
    @Test
    public void testEncodeHelo() {
        testEncode(SmtpRequests.helo("localhost"), "HELO localhost\r\n");
    }

}