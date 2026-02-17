class DummyClass_59597 {
	@Test
	public void getFirstLetterTest(){
		final String result = PinyinUtil.getFirstLetter("Hæ¯ç¬¬ä¸ä¸ª", ", ");
		Assert.assertEquals("h, s, d, y, g", result);
	}

}