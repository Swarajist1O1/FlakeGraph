class DummyClass_110142 {
	@Test
	public void testReportArgumentsLong() {
		String[] args = new String[] { "--report", "output/report.txt" };
		ClientConfiguration config = new ClientConfiguration(args);
		assertEquals("output/report.txt", config.getReportFileName());
	}

}