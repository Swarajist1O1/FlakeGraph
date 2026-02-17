class DummyClass_59636 {
	@Test
	public void jcsegTest() {
		TokenizerEngine engine = new JcsegEngine();
		Result result = engine.parse(text);
		checkResult(result);
	}

}