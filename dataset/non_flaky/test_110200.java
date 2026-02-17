class DummyClass_110200 {
	@Test
	public void missingDumpFile() throws IOException {
		MwLocalDumpFile df = new MwLocalDumpFile(
				"/non-existing-dump-file.json.gz");
		assertFalse(df.isAvailable());
	}

}