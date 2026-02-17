class DummyClass_59595 {
	@Test
	public void getPinyinByBopomofo4jTest() {
		final Bopomofo4jEngine engine = new Bopomofo4jEngine();
		final String pinyin = engine.getPinyin("ä½ å¥½h", " ");
		Assert.assertEquals("ni haoh", pinyin);
	}

}