class DummyClass_59640 {
	@Test
	public void wordTest() {
		TokenizerEngine engine = new WordEngine();
		Result result = engine.parse(text);
		String resultStr = IterUtil.join((Iterator<Word>)result, " ");
		Assert.assertEquals("è¿ä¸¤ä¸ª æ¹æ³ ç åºå« å¨äº è¿åå¼", resultStr);
	}

}