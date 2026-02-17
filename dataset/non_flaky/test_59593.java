class DummyClass_59593 {
	@Test
	public void getPinyinTest(){
		final String pinyin = PinyinUtil.getPinyin("ä½ å¥½", " ");
		Assert.assertEquals("ni hao", pinyin);
	}

}