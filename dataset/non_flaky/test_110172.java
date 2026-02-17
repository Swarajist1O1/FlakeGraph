class DummyClass_110172 {
	@Test
	public void timerStopReturnValues() {
		Timer timer1 = new Timer("stop test timer", Timer.RECORD_ALL);
		Timer timer2 = new Timer("stop test timer wall", Timer.RECORD_WALLTIME);

		timer1.start();
		timer2.start();
		doDummyComputation();
		long cpuTime1 = timer1.stop();
		long cpuTime2 = timer2.stop();

		assertEquals(cpuTime1, timer1.getTotalCpuTime());
		assertEquals(cpuTime2, -1);

		long cpuTime3 = timer1.stop();
		assertEquals(cpuTime3, -1);
	}

}