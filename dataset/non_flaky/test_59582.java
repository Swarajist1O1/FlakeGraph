class DummyClass_59582 {
	@Test
	public void sendWithLongNameFileTest() {
		//éä»¶åé¿åº¦å¤§äº60æ¶çæµè¯
		MailUtil.send("hutool@foxmail.com", "æµè¯", "<h1>é®ä»¶æ¥èªHutoolæµè¯</h1>", true, FileUtil.file("d:/6-LongLongä¸é¶æ®µå¹³å°å»ºè®¾å¨æ¥2018.3.12-3.16.xlsx"));
	}

}