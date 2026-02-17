class DummyClass_59641 {
	@Test
	public void mynlpTest() {
		// æ­¤ååæµè¯éè¦JDK8ï¼é»è®¤å¿½ç¥
		TokenizerEngine engine = new MynlpEngine();
		Result result = engine.parse(text);
		String resultStr = IterUtil.join((Iterator<Word>)result, " ");
		Assert.assertEquals("è¿ ä¸¤ä¸ª æ¹æ³ ç åºå« å¨äº è¿å å¼", resultStr);
	}

}