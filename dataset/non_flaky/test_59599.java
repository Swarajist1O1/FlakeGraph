class DummyClass_59599 {
	@Test
	public void getFirstLetterByBopomofo4jTest(){
		final Bopomofo4jEngine engine = new Bopomofo4jEngine();
		final String result = engine.getFirstLetter("ææµ·", "");
		Assert.assertEquals("lh", result);
	}

}