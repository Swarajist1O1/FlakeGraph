class DummyClass_110117 {
	@Test
	public void testQuietStdOutLoggingConfig() throws ParseException,
			IOException {
		String[] args = new String[] { "-a", "json", "-s" };
		new Client(mockDpc, args);

		assertEquals(Level.OFF, Client.consoleAppender.getThreshold());
		assertEquals(Level.WARN, Client.errorAppender.getThreshold());
	}

}