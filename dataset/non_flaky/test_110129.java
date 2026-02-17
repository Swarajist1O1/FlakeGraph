class DummyClass_110129 {
	@Test
	public void testReadConfigFile2() throws IOException {
		String configFile = "src/test/resources/testConf2.ini";
		String[] args = new String[] { "-c", configFile };
		ClientConfiguration config = new ClientConfiguration(args);

		assertFalse(config.getOfflineMode());
		assertFalse(config.isQuiet());
		assertEquals("testfile.json.gz", config.getInputDumpLocation());
		assertEquals("report.txt", config.getReportFileName());
		// remaining content was already tested above
	}

}