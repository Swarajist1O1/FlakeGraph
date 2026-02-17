class DummyClass_110130 {
	@Test
	public void testDefaultArguments() {
		String[] args = new String[] {};
		ClientConfiguration config = new ClientConfiguration(args);
		assertFalse(config.getOfflineMode());
		assertEquals(null, config.getDumpDirectoryLocation());
		assertEquals(null, config.getFilterLanguages());
		assertEquals(null, config.getFilterSiteKeys());
		assertEquals(null, config.getFilterProperties());
		assertEquals(null, config.getReportFileName());
		assertEquals(null, config.getInputDumpLocation());
		assertEquals(null, config.getLocalDumpFile());
		assertFalse(config.isQuiet());
	}

}