class DummyClass_59625 {
	@Test
	public void reconnectIfTimeoutTest() throws InterruptedException {
		Session session = JschUtil.getSession("sunnyserver", 22,"mysftp","liuyang1234");
		Sftp sftp = JschUtil.createSftp(session);

		Console.log("æå°pwd: " + sftp.pwd());
		Console.log("cd / : " + sftp.cd("/"));
		Console.log("ä¼ç ä¸æ®µæ¶é´ï¼æ¥çæ¯å¦è¶æ¶");
		Thread.sleep(30 * 1000);

		try{
			// å½è¿æ¥è¶æ¶æ¶ï¼isConnected()ä»ç¶è¿åtrueï¼pwdå½ä»¤ä¹è½æ­£å¸¸è¿åï¼å æ­¤ï¼å©ç¨åécdå½ä»¤çè¿åç»æï¼æ¥å¤æ­æ¯å¦è¿æ¥è¶æ¶
			Console.log("isConnected " + sftp.getClient().isConnected());
			Console.log("æå°pwd: " + sftp.pwd());
			Console.log("cd / : " + sftp.cd("/"));
		}catch (JschRuntimeException e) {
			e.printStackTrace();
		}

		Console.log("è°ç¨reconnectIfTimeoutæ¹æ³ï¼å¤æ­æ¯å¦è¶æ¶å¹¶éè¿");
		sftp.reconnectIfTimeout();

		Console.log("æå°pwd: " + sftp.pwd());

		IoUtil.close(sftp);
	}

}