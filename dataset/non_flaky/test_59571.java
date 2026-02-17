class DummyClass_59571 {
	@Test
	public void rangePatternTest() {
		CronPattern pattern = new CronPattern("* 20/2 * * * ?");
		assertMatch(pattern, "2017-02-09 04:20:00");
		assertMatch(pattern, "2017-02-09 05:20:00");
		assertMatch(pattern, "2017-02-19 04:22:33");

		pattern = new CronPattern("* 2-20/2 * * * ?");
		assertMatch(pattern, "2017-02-09 04:02:00");
		assertMatch(pattern, "2017-02-09 05:04:00");
		assertMatch(pattern, "2017-02-19 04:20:33");
	}

}