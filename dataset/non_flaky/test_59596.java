class DummyClass_59596 {
	@Test
	public void getPinyinUpperCaseTest(){
		final String pinyin = PinyinUtil.getPinyin("ä½ å¥½æ¡", " ");
		Assert.assertEquals("ni hao yi", pinyin);
	}

}