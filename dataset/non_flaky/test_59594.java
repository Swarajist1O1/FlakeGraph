class DummyClass_59594 {
	@Test
	public void getPinyinByPinyin4jTest() {
		final Pinyin4jEngine engine = new Pinyin4jEngine();
		final String pinyin = engine.getPinyin("ä½ å¥½h", " ");
		Assert.assertEquals("ni hao h", pinyin);
	}

}