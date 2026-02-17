class DummyClass_59587 {
	@Test
	public void parseSettingTest() {
		MailAccount account = GlobalMailAccount.INSTANCE.getAccount();
		account.getSmtpProps();
		
		Assert.assertNotNull(account.getCharset());
		Assert.assertTrue(account.isSslEnable());
	}

}