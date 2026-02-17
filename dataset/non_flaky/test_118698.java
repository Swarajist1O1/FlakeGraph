class DummyClass_118698 {
    @Test
    public void testEncodeHelp() {
        testEncode(SmtpRequests.help(null), "HELP\r\n");
    }

}