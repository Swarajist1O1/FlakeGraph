class DummyClass_110201 {
	@Test
	public void missingDumpFileDirectory() throws IOException {
		MwLocalDumpFile df = new MwLocalDumpFile(
				"/nonexisting-directory/non-existing-file.json.gz");
		assertFalse(df.isAvailable());
	}

}