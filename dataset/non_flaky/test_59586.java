class DummyClass_59586 {
	@Test
	public void mailAccountTest() {
		MailAccount account = new MailAccount();
		account.setFrom("hutool@yeah.net");
		account.setDebug(true);
		account.defaultIfEmpty();
		Properties props = account.getSmtpProps();
		Assert.assertEquals("true", props.getProperty("mail.debug"));
	}

}