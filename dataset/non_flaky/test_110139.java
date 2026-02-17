class DummyClass_110139 {
	@Test
	public void testQuietArgumentsShort() {
		String[] args = new String[] { "-q" };
		ClientConfiguration config = new ClientConfiguration(args);
		assertTrue(config.isQuiet());
	}

}