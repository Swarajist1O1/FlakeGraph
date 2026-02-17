class DummyClass_59566 {
	@Test
	public void matchAllTest() {
		CronPattern pattern;
		// ä»»ä½æ¶é´å¹é
		pattern = new CronPattern("* * * * * *");
		Assert.assertTrue(pattern.match(DateUtil.current(), true));
		Assert.assertTrue(pattern.match(DateUtil.current(), false));
	}

}