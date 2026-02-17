class DummyClass_118690 {
    @Test
    public void isContentExpected() {
        assertTrue(SmtpCommand.valueOf("DATA").isContentExpected());
        assertTrue(SmtpCommand.valueOf("data").isContentExpected());

        assertFalse(SmtpCommand.HELO.isContentExpected());
        assertFalse(SmtpCommand.HELP.isContentExpected());
        assertFalse(SmtpCommand.valueOf("DATA2").isContentExpected());
    }

}