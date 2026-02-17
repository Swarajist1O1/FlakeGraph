class DummyClass_59570 {
	@Test
	public void patternTest() {
		CronPattern pattern = new CronPattern("* 0 4 * * ?");
		assertMatch(pattern, "2017-02-09 04:00:00");
		assertMatch(pattern, "2017-02-19 04:00:33");

		// 6ä½Quartzé£æ ¼è¡¨è¾¾å¼
		pattern = new CronPattern("* 0 4 * * ?");
		assertMatch(pattern, "2017-02-09 04:00:00");
		assertMatch(pattern, "2017-02-19 04:00:33");
	}

}