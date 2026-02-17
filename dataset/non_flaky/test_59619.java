class DummyClass_59619 {
	@Test
	public void toUnicodeTest() {
		String emoji = EmojiUtil.toUnicode(":smile:");
		Assert.assertEquals("ð", emoji);
	}

}