class DummyClass_59633 {
	@Test
	public void createEngineTest() {
		// é»è®¤åè¯å¼æï¼æ­¤å¤ä¸ºAnsj
		TokenizerEngine engine = TokenizerUtil.createEngine();
		Result result = engine.parse(text);
		checkResult(result);
	}

}