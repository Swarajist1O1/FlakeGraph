class DummyClass_110140 {
	@Test
	public void testQuietArgumentsLong() {
		String[] args = new String[] { "--quiet" };
		ClientConfiguration config = new ClientConfiguration(args);
		assertTrue(config.isQuiet());
	}

}