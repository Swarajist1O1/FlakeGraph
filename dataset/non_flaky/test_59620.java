class DummyClass_59620 {
	@Test
	public void toAliasTest() {
		String alias = EmojiUtil.toAlias("ð");
		Assert.assertEquals(":smile:", alias);
	}

}