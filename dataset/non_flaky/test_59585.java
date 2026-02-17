class DummyClass_59585 {
	@Test
	public void sendByAccountTest() {
		MailAccount account = new MailAccount();
		account.setHost("smtp.yeah.net");
		account.setPort(465);
		account.setSslEnable(true);
		account.setFrom("hutool@yeah.net");
		account.setUser("hutool");
		account.setPass("q1w2e3");
		MailUtil.send(account, "914104645@qq.com", "æµè¯", "<h1>é®ä»¶æ¥èªHutoolæµè¯</h1>", true);
	}

}