class DummyClass_59581 {
	@Test
	public void sendWithFileTest() {
		MailUtil.send("hutool@foxmail.com", "æµè¯", "<h1>é®ä»¶æ¥èªHutoolæµè¯</h1>", true, FileUtil.file("d:/æµè¯éä»¶ææ¬.txt"));
	}

}