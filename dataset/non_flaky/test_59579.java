class DummyClass_59579 {
	@Test
	public void cronWithListenerTest() {
		CronUtil.getScheduler().addListener(new TaskListener() {
			@Override
			public void onStart(TaskExecutor executor) {
				Console.log("Found task:[{}] start!", executor.getCronTask().getId());
			}

}