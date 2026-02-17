class DummyClass_59635 {
	@Test
	public void ikAnalyzerTest() {
		TokenizerEngine engine = new IKAnalyzerEngine();
		Result result = engine.parse(text);
		String resultStr = IterUtil.join((Iterator<Word>)result, " ");
		Assert.assertEquals("è¿ä¸¤ä¸ª æ¹æ³ ç åºå« å¨äº è¿åå¼", resultStr);
	}

}