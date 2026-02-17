class DummyClass_59623 {
	@Test
	public void bindRemotePort() throws InterruptedException {
		// å»ºç«ä¼è¯
		Session session = JschUtil.getSession("looly.centos", 22, "test", "123456");
		// ç»å®sshæå¡ç«¯8089ç«¯å£å°æ¬æºç8000ç«¯å£ä¸
		boolean b = JschUtil.bindRemotePort(session, 8089, "localhost", 8000);
		Assert.assertTrue(b);
		// ä¿è¯ä¸ç´è¿è¡
//		while (true){
//			Thread.sleep(3000);
//		}
	}

}