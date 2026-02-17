class DummyClass_59598 {
	@Test
	public void getFirstLetterByPinyin4jTest(){
		final Pinyin4jEngine engine = new Pinyin4jEngine();
		final String result = engine.getFirstLetter("ææµ·", "");
		Assert.assertEquals("lh", result);
	}

}