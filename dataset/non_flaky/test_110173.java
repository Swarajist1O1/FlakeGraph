class DummyClass_110173 {
	@Test
	public void enableCpuTimeTaking() {
		ThreadMXBean tmxb = ManagementFactory.getThreadMXBean();
		tmxb.setThreadCpuTimeEnabled(false);

		Timer timer = new Timer("Test timer", Timer.RECORD_ALL);
		timer.start();
		doDummyComputation();
		timer.stop();

		assertTrue("Timer should have measured a CPU time.",
				timer.getTotalCpuTime() > 0);
	}

}