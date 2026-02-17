class DummyClass_59638 {
	@Test
	public void mmsegTest() {
		TokenizerEngine engine = new MmsegEngine();
		Result result = engine.parse(text);
		checkResult(result);
	}

}