class DummyClass_110116 {
	@Test
	public void testDefaultLoggingConfig() throws ParseException, IOException {
		String[] args = new String[] {};
		Client client = new Client(mockDpc, args);
		client.performActions(); // print help

		assertEquals(Level.INFO, Client.consoleAppender.getThreshold());
		assertEquals(Level.WARN, Client.errorAppender.getThreshold());
	}

}