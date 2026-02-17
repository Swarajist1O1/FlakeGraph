class DummyClass_59569 {
	@Test
	public void CronPatternTest2() {
		CronPattern pattern = new CronPattern("0/30 * * * *");
		Assert.assertTrue(pattern.match(DateUtil.parse("2018-10-09 12:00:00").getTime(), false));
		Assert.assertTrue(pattern.match(DateUtil.parse("2018-10-09 12:30:00").getTime(), false));
		
		pattern = new CronPattern("32 * * * *");
		Assert.assertTrue(pattern.match(DateUtil.parse("2018-10-09 12:32:00").getTime(), false));
	}

}