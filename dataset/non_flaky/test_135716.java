class DummyClass_135716 {
	@Test
	public void shouldYieldNoExecutedTasksOnStartup() throws Exception {
		//given
		final Collection<HystrixThreadPoolMetrics> instances = HystrixThreadPoolMetrics.getInstances();

		//then
		assertEquals(0, instances.size());

	}

}