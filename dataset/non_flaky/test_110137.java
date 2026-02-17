class DummyClass_110137 {
	@Test
	public void testStdOutOutputArgumentsShort() {
		String[] args = new String[] { "-a", "json", "-s" };
		ClientConfiguration config = new ClientConfiguration(args);
		assertTrue(config.isQuiet());
	}

}