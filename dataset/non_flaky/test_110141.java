class DummyClass_110141 {
	@Test
	public void testReportArgumentsShort() {
		String[] args = new String[] { "-r", "output/report.txt" };
		ClientConfiguration config = new ClientConfiguration(args);
		assertEquals("output/report.txt", config.getReportFileName());
	}

}