class DummyClass_59621 {
	@Test
	public void containsEmojiTest() {
		boolean containsEmoji = EmojiUtil.containsEmoji("æµè¯ä¸ä¸æ¯å¦åå«EMOJ:ð");
		Assert.assertEquals(containsEmoji, true);
		boolean notContainsEmoji = EmojiUtil.containsEmoji("ä¸åå«EMOJ:^_^");
		Assert.assertEquals(notContainsEmoji, false);

	}

}