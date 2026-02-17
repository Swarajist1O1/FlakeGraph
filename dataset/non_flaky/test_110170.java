class DummyClass_110170 {
	@Test
	public void basicTimerOperation() {
		Timer timer = new Timer("Test timer", Timer.RECORD_ALL);
		assertEquals(timer.getName(), "Test timer");
		long threadId = timer.getThreadId();

		assertEquals(timer.getAvgCpuTime(), 0);
		assertEquals(timer.getAvgWallTime(), 0);

		ThreadMXBean tmxb = ManagementFactory.getThreadMXBean();
		if (!tmxb.isThreadCpuTimeEnabled()) {
			tmxb.setThreadCpuTimeEnabled(true);
		}

		long cpuTime1 = tmxb.getThreadCpuTime(threadId);
		long wallTime1 = System.nanoTime();
		timer.start();
		doDummyComputation();
		assertTrue("Timer should be running", timer.isRunning());
		timer.stop();
		cpuTime1 = tmxb.getThreadCpuTime(threadId) - cpuTime1;
		wallTime1 = System.nanoTime() - wallTime1;
		assertTrue(
				"Unrealistic CPU time: " + timer.getTotalCpuTime()
						+ " should be closer to " + cpuTime1,
				(cpuTime1 - TimerTest.TIME_TOLERANCE) <= timer
						.getTotalCpuTime()
						&& timer.getTotalCpuTime() <= cpuTime1);
		assertTrue(
				"Unrealistic wall time: " + timer.getTotalWallTime()
						+ " should be closer to " + wallTime1,
				(wallTime1 - 2 * TimerTest.TIME_TOLERANCE) <= timer
						.getTotalWallTime()
						&& timer.getTotalWallTime() <= wallTime1);

		long cpuTime2 = tmxb.getThreadCpuTime(threadId);
		long wallTime2 = System.nanoTime();
		timer.start();
		doDummyComputation();
		timer.stop();
		cpuTime1 += tmxb.getThreadCpuTime(threadId) - cpuTime2;
		wallTime1 += System.nanoTime() - wallTime2;
		assertTrue(
				"Unrealistic total CPU time: " + timer.getTotalCpuTime()
						+ " should be closer to " + cpuTime1,
				(cpuTime1 - 2 * TimerTest.TIME_TOLERANCE) <= timer
						.getTotalCpuTime()
						&& timer.getTotalCpuTime() <= cpuTime1);
		assertTrue(
				"Unrealistic total wall time: " + timer.getTotalWallTime()
						+ " should be closer to " + wallTime1,
				(wallTime1 - 4 * TimerTest.TIME_TOLERANCE) <= timer
						.getTotalWallTime()
						&& timer.getTotalWallTime() <= wallTime1);

		assertEquals(timer.getTotalCpuTime() / 2, timer.getAvgCpuTime());
		assertEquals(timer.getTotalWallTime() / 2, timer.getAvgWallTime());

		timer.reset();
		assertEquals(timer.getTotalCpuTime(), 0);
		assertEquals(timer.getTotalWallTime(), 0);
		assertFalse("Timer should not be running", timer.isRunning());
	}

}