class DummyClass_59580 {
	@Test
	public void addAndRemoveTest() {
		String id = CronUtil.schedule("*/2 * * * * *", (Runnable) () -> Console.log("task running : 2s"));

		Console.log(id);
		CronUtil.remove(id);

		// æ¯æç§çº§å«å®æ¶ä»»å¡
		CronUtil.setMatchSecond(true);
		CronUtil.start();
	}

}