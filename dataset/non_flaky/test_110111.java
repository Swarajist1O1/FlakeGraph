class DummyClass_110111 {
	@Test
	public void testJsonGzipOutput() throws IOException {
		String[] args = new String[] { "-a", "json", "-o",
				"/path/to/output.json", "-z", "gz" };

		DirectoryManagerFactory
				.setDirectoryManagerClass(MockDirectoryManager.class);

		ClientConfiguration config = new ClientConfiguration(args);
		JsonSerializationAction jsa = (JsonSerializationAction) config
				.getActions().get(0);

		ItemIdValue subject1 = Datamodel.makeWikidataItemIdValue("Q42");
		MonolingualTextValue mtv1 = Datamodel.makeMonolingualTextValue("Test1",
				"en");
		MonolingualTextValue mtv2 = Datamodel.makeMonolingualTextValue("Test2",
				"fr");

		ItemDocument id1 = Datamodel.makeItemDocument(subject1,
				Arrays.asList(mtv1, mtv2), Arrays.asList(mtv1),
				Collections.<MonolingualTextValue> emptyList(),
				Collections.<StatementGroup> emptyList(),
				Collections.<String, SiteLink> emptyMap());

		jsa.open();
		jsa.processItemDocument(id1);
		jsa.close();

		MockDirectoryManager mdm = new MockDirectoryManager(
				Paths.get("/path/to/"), false);

		ObjectMapper mapper = new DatamodelMapper(Datamodel.SITE_WIKIDATA);
		ObjectReader documentReader = mapper.readerFor(EntityDocumentImpl.class);
		MappingIterator<EntityDocument> documentIterator = documentReader
				.readValues(mdm.getInputStreamForFile("output.json.gz",
						CompressionType.GZIP));

		List<EntityDocument> results = new ArrayList<>();
		while (documentIterator.hasNextValue()) {
			EntityDocument document = documentIterator.nextValue();
			results.add(document);
		}
		documentIterator.close();

		assertEquals(1, results.size());
		assertEquals(id1, results.get(0));
	}

}