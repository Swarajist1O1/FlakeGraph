class DummyClass_59578 {
	@Test
	public void cronTest() {
		// æ¯æç§çº§å«å®æ¶ä»»å¡
		CronUtil.setMatchSecond(true);
		CronUtil.getScheduler().setDaemon(false);
		CronUtil.start();

		ThreadUtil.waitForDie();
		CronUtil.stop();
	}

}