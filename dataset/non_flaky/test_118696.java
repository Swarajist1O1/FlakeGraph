class DummyClass_118696 {
    @Test
    public void testEncodeNoop() {
        testEncode(SmtpRequests.noop(), "NOOP\r\n");
    }

}