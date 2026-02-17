class DummyClass_118689 {
    @Test
    public void equalsIgnoreCase() {
        assertEquals(SmtpCommand.MAIL, SmtpCommand.valueOf("mail"));
        assertEquals(SmtpCommand.valueOf("test"), SmtpCommand.valueOf("TEST"));
    }

}