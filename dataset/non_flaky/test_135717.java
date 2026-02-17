class DummyClass_135717 {
	@Test
	public void shouldReturnOneExecutedTask() throws Exception {
		//given
		final Collection<HystrixThreadPoolMetrics> instances = HystrixThreadPoolMetrics.getInstances();
		RollingThreadPoolEventCounterStream.getInstance(tpKey, 10, 100).startCachingStreamValuesIfUnstarted();

		//when
		new NoOpHystrixCommand().execute();

		//then
		Thread.sleep(100);
		assertEquals(1, instances.size());
		assertEquals(1, instances.iterator().next().getRollingCountThreadsExecuted());
	}

}