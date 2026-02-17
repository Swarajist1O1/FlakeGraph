class DummyClass_110149 {
	@Test
	public void testLocalDumpFileLong() {
		DirectoryManagerFactory
				.setDirectoryManagerClass(MockDirectoryManager.class);
		String[] args = new String[] { "--input", "dumptest.json" };
		ClientConfiguration config = new ClientConfiguration(args);

		MwDumpFile df = config.getLocalDumpFile();

		assertEquals("dumptest.json", config.getInputDumpLocation());
		assertTrue(df instanceof MwLocalDumpFile);
		MwLocalDumpFile ldf = (MwLocalDumpFile) df;

		assertEquals(Paths.get("dumptest.json").toAbsolutePath(), ldf.getPath());
	}

}