class DummyClass_59568 {
	@Test
	public void cronPatternTest() {
		CronPattern pattern;

		// 12:11å¹é
		pattern = new CronPattern("39 11 12 * * *");
		assertMatch(pattern, "12:11:39");

		// æ¯5åéå¹éï¼å¹éåéä¸ºï¼[0,5,10,15,20,25,30,35,40,45,50,55]
		pattern = new CronPattern("39 */5 * * * *");
		assertMatch(pattern, "12:00:39");
		assertMatch(pattern, "12:05:39");
		assertMatch(pattern, "12:10:39");
		assertMatch(pattern, "12:15:39");
		assertMatch(pattern, "12:20:39");
		assertMatch(pattern, "12:25:39");
		assertMatch(pattern, "12:30:39");
		assertMatch(pattern, "12:35:39");
		assertMatch(pattern, "12:40:39");
		assertMatch(pattern, "12:45:39");
		assertMatch(pattern, "12:50:39");
		assertMatch(pattern, "12:55:39");

		// 2:01,3:01,4:01
		pattern = new CronPattern("39 1 2-4 * * *");
		assertMatch(pattern, "02:01:39");
		assertMatch(pattern, "03:01:39");
		assertMatch(pattern, "04:01:39");

		// 2:01,3:01,4:01
		pattern = new CronPattern("39 1 2,3,4 * * *");
		assertMatch(pattern, "02:01:39");
		assertMatch(pattern, "03:01:39");
		assertMatch(pattern, "04:01:39");

		// 08-07, 08-06
		pattern = new CronPattern("39 0 0 6,7 8 *");
		assertMatch(pattern, "2016-08-07 00:00:39");
		assertMatch(pattern, "2016-08-06 00:00:39");

		// å«åå¿½ç¥å¤§å°å
		pattern = new CronPattern("39 0 0 6,7 Aug *");
		assertMatch(pattern, "2016-08-06 00:00:39");
		assertMatch(pattern, "2016-08-07 00:00:39");

		pattern = new CronPattern("39 0 0 7 aug *");
		assertMatch(pattern, "2016-08-07 00:00:39");

		// ææå
		pattern = new CronPattern("39 0 0 * * Thu");
		assertMatch(pattern, "2017-02-09 00:00:39");
		assertMatch(pattern, "2017-02-09 00:00:39");

	}

}