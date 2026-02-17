class DummyClass_110138 {
	@Test
	public void testStdOutOutputArgumentsLong() {
		String[] args = new String[] { "--action", "json", "--stdout" };
		ClientConfiguration config = new ClientConfiguration(args);
		assertTrue(config.isQuiet());
	}

}