class DummyClass_110132 {
	@Test
	public void testUnknownArguments() {
		String[] args = new String[] { "--unknown", "-foo" };
		ClientConfiguration config = new ClientConfiguration(args);
		assertFalse(config.getOfflineMode());
		assertEquals(null, config.getDumpDirectoryLocation());
		assertFalse(config.isQuiet());
	}

}