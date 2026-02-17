class DummyClass_59577 {
	@Test
	public void customCronTest() {
		CronUtil.schedule("*/2 * * * * *", (Task) () -> Console.log("Task excuted."));

		// æ¯æç§çº§å«å®æ¶ä»»å¡
		CronUtil.setMatchSecond(true);
		CronUtil.start();

		ThreadUtil.waitForDie();
		Console.log("Exit.");
	}

}