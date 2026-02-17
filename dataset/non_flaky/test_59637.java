class DummyClass_59637 {
	@Test
	public void jiebaTest() {
		TokenizerEngine engine = new JiebaEngine();
		Result result = engine.parse(text);
		String resultStr = IterUtil.join((Iterator<Word>)result, " ");
		Assert.assertEquals("è¿ ä¸¤ä¸ª æ¹æ³ ç åºå« å¨äº è¿åå¼", resultStr);
	}

}