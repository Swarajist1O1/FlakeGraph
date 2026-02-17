class DummyClass_59567 {
	@Test
	public void matchAllTest2() {
		// å¨5ä½è¡¨è¾¾å¼ä¸­ï¼ç§é¨åå¹¶ä¸æ¯ä»»æå¹éï¼èæ¯ä¸ä¸ªåºå®å¼
		// å æ­¤æ­¤å¤å¹éå°±ä¸è½å¹éç§
		CronPattern pattern;
		// ä»»ä½æ¶é´å¹é
		pattern = new CronPattern("* * * * *");
		for(int i = 0; i < 1; i++) {
			Assert.assertTrue(pattern.match(DateUtil.current(), false));
		}
	}

}