class DummyClass_110118 {
	@Test
	public void testQuietLoggingConfig() throws ParseException, IOException {
		String[] TEST_ARGS = new String[] { "-a", "json", "-q" };
		new Client(mockDpc, TEST_ARGS);

		assertEquals(Level.OFF, Client.consoleAppender.getThreshold());
		assertEquals(Level.WARN, Client.errorAppender.getThreshold());
	}

}