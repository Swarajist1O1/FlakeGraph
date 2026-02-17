class DummyClass_59622 {
	@Test
	public void bindPortTest() {
		//æ°å»ºä¼è¯ï¼æ­¤ä¼è¯ç¨äºsshè¿æ¥å°è·³æ¿æºï¼å ¡åæºï¼ï¼æ­¤å¤ä¸º10.1.1.1:22
		Session session = JschUtil.getSession("looly.centos", 22, "test", "123456");
		// å°å ¡åæºä¿æ¤çåç½8080ç«¯å£æ å°å°localhostï¼æä»¬å°±å¯ä»¥éè¿è®¿é®http://localhost:8080/è®¿é®åç½æå¡äº
		JschUtil.bindPort(session, "172.20.12.123", 8080, 8080);
	}

}