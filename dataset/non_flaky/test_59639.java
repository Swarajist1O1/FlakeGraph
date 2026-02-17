class DummyClass_59639 {
	@Test
	public void smartcnTest() {
		TokenizerEngine engine = new SmartcnEngine();
		Result result = engine.parse(text);
		String resultStr = IterUtil.join((Iterator<Word>)result, " ");
		Assert.assertEquals("è¿ ä¸¤ ä¸ª æ¹æ³ ç åºå« å¨äº è¿å å¼", resultStr);
	}

}