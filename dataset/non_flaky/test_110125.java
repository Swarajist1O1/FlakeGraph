class DummyClass_110125 {
	@Test
	public void testWriteReport() throws IOException {
		DirectoryManagerFactory
				.setDirectoryManagerClass(MockDirectoryManager.class);

		MockDirectoryManager mdm = new MockDirectoryManager(
				Paths.get("/output/"), false);

		String[] args = {"-n", "-a", "rdf", "--rdftasks", "aliases", "-o",
				"/output/wikidata.rdf", "-r", "/output/report.txt" };

		Client client = new Client(mockDpc, args);
		DumpProcessingAction action = client.clientConfiguration.actions.get(0);
		action.open();
		action.close();
		client.writeReport();
		assertTrue(IOUtils
				.toString(
						mdm.getInputStreamForFile("report.txt",
								CompressionType.NONE))
				.matches(
						"RdfSerializationAction: Finished serialization of \\d+ RDF triples in file /output/wikidata.rdf"
								+ System.lineSeparator()));

	}

}