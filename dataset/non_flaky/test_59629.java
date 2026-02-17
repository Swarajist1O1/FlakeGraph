class DummyClass_59629 {
	@Test
	public void reconnectIfTimeoutTest() throws InterruptedException {
		Ftp ftp = new Ftp("looly.centos");

		Console.log("æå°pwd: " + ftp.pwd());

		Console.log("ä¼ç ä¸æ®µæ¶é´ï¼ç¶ååæ¬¡åépwdå½ä»¤ï¼æåºå¼å¸¸è¡¨æè¿æ¥è¶æ¶");
		Thread.sleep(35 * 1000);

		try{
			Console.log("æå°pwd: " + ftp.pwd());
		}catch (FtpException e) {
			e.printStackTrace();
		}

		Console.log("å¤æ­æ¯å¦è¶æ¶å¹¶éè¿...");
		ftp.reconnectIfTimeout();

		Console.log("æå°pwd: " + ftp.pwd());

		IoUtil.close(ftp);
	}

}