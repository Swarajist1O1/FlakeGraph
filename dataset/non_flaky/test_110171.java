class DummyClass_110171 {
	@Test
	public void namedTimers() {
		Timer timerA1 = Timer.getNamedTimer("test timer");
		Timer timerA2 = Timer.getNamedTimer("test timer");
		Timer timerA3 = Timer.getNamedTimer("test timer", Timer.RECORD_ALL);
		Timer timerA4 = Timer.getNamedTimer("test timer", Timer.RECORD_ALL,
				timerA1.getThreadId());
		Timer timerCpu = Timer
				.getNamedTimer("test timer", Timer.RECORD_CPUTIME);
		Timer timerWall = Timer.getNamedTimer("test timer",
				Timer.RECORD_WALLTIME);
		Timer timerNoThread = Timer.getNamedTimer("test timer",
				Timer.RECORD_ALL, 0);
		Timer timerNone = Timer.getNamedTimer("test timer none",
				Timer.RECORD_NONE);
		Timer timerB = Timer.getNamedTimer("test timer 2");

		// Testing Timer equality:
		assertEquals(timerA1, timerA2);
		assertEquals(timerA1, timerA3);
		assertEquals(timerA1, timerA4);
		assertNotEquals(timerA1, timerCpu);
		assertNotEquals(timerA1, timerWall);
		assertNotEquals(timerA1, timerNoThread);
		assertNotEquals(timerA1, timerB);
		assertNotEquals(timerA1, this);

		// Testing start/stop operation:
		Timer.startNamedTimer("test timer");
		Timer.startNamedTimer("test timer", Timer.RECORD_CPUTIME);
		Timer.startNamedTimer("test timer", Timer.RECORD_WALLTIME);
		Timer.startNamedTimer("test timer", Timer.RECORD_ALL, 0);
		doDummyComputation();
		Timer.stopNamedTimer("test timer");
		Timer.stopNamedTimer("test timer", Timer.RECORD_CPUTIME);
		Timer.stopNamedTimer("test timer", Timer.RECORD_WALLTIME);
		Timer.stopNamedTimer("test timer", Timer.RECORD_ALL, 0);

		assertTrue("Named timer should have measured a non-zero CPU time.",
				timerA1.getTotalCpuTime() > 0);
		assertTrue("Named timer should have measured a non-zero wall time.",
				timerA1.getTotalWallTime() > 0);
		assertTrue(
				"Timer for CPU time should have measured a non-zero CPU time.",
				timerCpu.getTotalCpuTime() > 0);
		assertTrue("Timer for CPU time should not have measured a wall time.",
				timerCpu.getTotalWallTime() == 0);
		assertTrue("Timer for wall time should not have measured a CPU time.",
				timerWall.getTotalCpuTime() == 0);
		assertTrue(
				"Timer for wall time should have measured a non-zero wall time.",
				timerWall.getTotalWallTime() > 0);
		assertTrue(
				"Timer without threadId should not have measured a CPU time.",
				timerNoThread.getTotalCpuTime() == 0);
		assertTrue(
				"Timer without threadId should have measured a non-zero wall time.",
				timerNoThread.getTotalWallTime() > 0);

		// Testing total timer creation:
		Timer totalTimer1 = Timer.getNamedTotalTimer("test timer");
		// There should be four *distinct* timers of that name
		assertEquals(totalTimer1.getTotalCpuTime(), timerA1.getTotalCpuTime()
				+ timerCpu.getTotalCpuTime() + timerWall.getTotalCpuTime()
				+ timerNoThread.getTotalCpuTime());
		assertEquals(totalTimer1.getTotalWallTime(), timerA1.getTotalWallTime()
				+ timerCpu.getTotalWallTime() + timerWall.getTotalWallTime()
				+ timerNoThread.getTotalWallTime());

		Timer totalTimer2 = Timer.getNamedTotalTimer("test timer 2");
		// There should be just one timer of that name
		assertEquals(totalTimer2, timerB);

		// Testing toString operation
		assertTrue(timerA1.toString().startsWith(
				"Time for test timer (thread " + timerA1.getThreadId()
						+ ") for 1 run(s) CPU/Wall/CPU avg/Wall avg (ms):"));
		assertTrue(timerCpu.toString().startsWith(
				"Time for test timer (thread " + timerCpu.getThreadId()
						+ ") for 1 run(s) CPU/CPU avg (ms):"));
		assertTrue(timerWall.toString().startsWith(
				"Time for test timer (thread " + timerWall.getThreadId()
						+ ") for 1 run(s) Wall/Wall avg (ms):"));
		assertTrue(totalTimer1.toString().startsWith(
				"Time for test timer (over 4 threads)"));
		assertTrue(timerNoThread.toString().startsWith(
				"Time for test timer for 1 run(s)"));
		assertEquals(timerNone.toString(), "Timer test timer none (thread "
				+ timerNone.getThreadId()
				+ ") recorded 0 run(s); no times taken");
		timerA1.start();
		assertTrue(timerA1.toString().endsWith("[timer running!]"));

		// Testing reset operation:
		Timer.resetNamedTimer("test timer");
		Timer.resetNamedTimer("test timer", Timer.RECORD_CPUTIME);
		Timer.resetNamedTimer("test timer", Timer.RECORD_WALLTIME);
		Timer.resetNamedTimer("test timer", Timer.RECORD_ALL, 0);

		assertTrue("Named timer should have reset CPU time.",
				timerA1.getTotalCpuTime() == 0);
		assertTrue("Named timer should have reset wall time.",
				timerA1.getTotalWallTime() == 0);
		assertTrue("Timer for CPU time should have reset CPU time.",
				timerCpu.getTotalCpuTime() == 0);
		assertTrue("Timer for CPU time should have reset wall time.",
				timerCpu.getTotalWallTime() == 0);
		assertTrue("Timer for wall time should have reset CPU time.",
				timerWall.getTotalCpuTime() == 0);
		assertTrue("Timer for wall time should have reset wall time.",
				timerWall.getTotalWallTime() == 0);
		assertTrue("Timer without threadId should have reset CPU time.",
				timerNoThread.getTotalCpuTime() == 0);
		assertTrue("Timer without threadId should have reset wall time.",
				timerNoThread.getTotalWallTime() == 0);

		// Testing unregistered timer stop (does not create one):
		assertEquals(Timer.stopNamedTimer("unknown name"), -1);
	}

}