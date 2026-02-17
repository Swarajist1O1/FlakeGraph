class DummyClass_118699 {
    @Test
    public void testEncodeHelpWithArg() {
        testEncode(SmtpRequests.help("MAIL"), "HELP MAIL\r\n");
    }

}