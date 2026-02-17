class DummyClass_59572 {
	@Test
	public void lastTest() {
		// æ¯ææåä¸å¤©çä»»ææ¶é´
		CronPattern pattern = new CronPattern("* * * L * ?");
		assertMatch(pattern, "2017-07-31 04:20:00");
		assertMatch(pattern, "2017-02-28 04:20:00");

		// æåä¸ä¸ªæçä»»ææ¶é´
		pattern = new CronPattern("* * * * L ?");
		assertMatch(pattern, "2017-12-02 04:20:00");

		// ä»»æå¤©çæåæ¶é´
		pattern = new CronPattern("L L L * * ?");
		assertMatch(pattern, "2017-12-02 23:59:59");
	}

}