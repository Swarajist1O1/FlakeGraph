class DummyClass_110126 {
	@Test
	public void testInsertDumpInformation() throws IOException {
		DirectoryManagerFactory
				.setDirectoryManagerClass(MockDirectoryManager.class);

		MockDirectoryManager mdm = new MockDirectoryManager(
				Paths.get("/output/"), false);

		String[] args = { "-n", "-a", "rdf", "-o", "/output/wikidata.rdf",
				"--rdftasks", "aliases", "-r", "/output/report-{DATE}.txt" };

		Client client = new Client(mockDpc, args);
		client.performActions();

		assertTrue(mdm.hasFile("/output/report-"
				+ client.clientConfiguration.getDateStamp() + ".txt"));
	}

}