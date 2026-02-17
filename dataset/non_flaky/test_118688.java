class DummyClass_118688 {
    @Test
    public void getCommandFromCache() {
        assertSame(SmtpCommand.DATA, SmtpCommand.valueOf("DATA"));
        assertSame(SmtpCommand.EHLO, SmtpCommand.valueOf("EHLO"));
        assertNotSame(SmtpCommand.EHLO, SmtpCommand.valueOf("ehlo"));
    }

}