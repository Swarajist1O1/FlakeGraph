class DummyClass_59634 {
	@Test
	public void hanlpTest() {
		TokenizerEngine engine = new HanLPEngine();
		Result result = engine.parse(text);
		String resultStr = IterUtil.join((Iterator<Word>)result, " ");
		Assert.assertEquals("è¿ ä¸¤ ä¸ª æ¹æ³ ç åºå« å¨äº è¿å å¼", resultStr);
	}

}