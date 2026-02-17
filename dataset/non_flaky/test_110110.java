class DummyClass_110110 {
	@Test
	public void testJsonOutput() throws IOException {
		String[] args = new String[] { "-a", "json", "-o",
				"/path/to/output.json" };

		DirectoryManagerFactory
				.setDirectoryManagerClass(MockDirectoryManager.class);

		ClientConfiguration config = new ClientConfiguration(args);
		JsonSerializationAction jsa = (JsonSerializationAction) config
				.getActions().get(0);

		ItemIdValue subject1 = Datamodel.makeWikidataItemIdValue("Q42");
		ItemIdValue subject2 = Datamodel.makeWikidataItemIdValue("Q43");
		MonolingualTextValue mtv1 = Datamodel.makeMonolingualTextValue("Test1",
				"en");
		MonolingualTextValue mtv2 = Datamodel.makeMonolingualTextValue("Test2",
				"fr");

		ItemDocument id1 = Datamodel.makeItemDocument(subject1,
				Arrays.asList(mtv1, mtv2), Arrays.asList(mtv1),
				Collections.<MonolingualTextValue> emptyList(),
				Collections.<StatementGroup> emptyList(),
				Collections.<String, SiteLink> emptyMap());

		ItemDocument id2 = Datamodel.makeItemDocument(subject2,
				Collections.<MonolingualTextValue> emptyList(),
				Arrays.asList(mtv2),
				Collections.<MonolingualTextValue> emptyList(),
				Collections.<StatementGroup> emptyList(),
				Collections.<String, SiteLink> emptyMap());

		PropertyDocument pd1 = Datamodel
				.makePropertyDocument(
						Datamodel.makeWikidataPropertyIdValue("P31"),
						Arrays.asList(mtv1),
						Collections.<MonolingualTextValue> emptyList(),
						Arrays.asList(mtv1),
						Collections.emptyList(),
						Datamodel
								.makeDatatypeIdValue(DatatypeIdValue.DT_MONOLINGUAL_TEXT));

		jsa.open();
		jsa.processItemDocument(id1);
		jsa.processPropertyDocument(pd1);
		jsa.processItemDocument(id2);
		jsa.close();

		MockDirectoryManager mdm = new MockDirectoryManager(
				Paths.get("/path/to/"), false);

		ObjectMapper mapper = new DatamodelMapper(Datamodel.SITE_WIKIDATA);
		ObjectReader documentReader = mapper
				.readerFor(EntityDocumentImpl.class);
		MappingIterator<EntityDocument> documentIterator = documentReader
				.readValues(mdm.getInputStreamForFile("output.json",
						CompressionType.NONE));

		List<EntityDocument> results = new ArrayList<>();
		while (documentIterator.hasNextValue()) {
			EntityDocument document = documentIterator.nextValue();
			results.add(document);
		}
		documentIterator.close();

		assertEquals(3, results.size());
		assertEquals(id1, results.get(0));
		assertEquals(pd1, results.get(1));
		assertEquals(id2, results.get(2));

	}

}