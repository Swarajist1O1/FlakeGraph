class DummyClass_110128 {
	@Test
	public void testReadConfigFile() throws IOException {
		String configFile = "src/test/resources/testConf.ini";
		String[] args = new String[] { "-c", configFile };
		ClientConfiguration config = new ClientConfiguration(args);

		assertTrue(config.getOfflineMode());
		assertTrue(config.isQuiet());
		assertEquals("dumps/wikidata/", config.getDumpDirectoryLocation());
		assertEquals(Collections.<String> emptySet(),
				config.getFilterSiteKeys());
		assertEquals(Collections.singleton(Datamodel
						.makeWikidataPropertyIdValue("P31")),
				config.getFilterProperties());
		Set<String> langFilters = new HashSet<>();
		langFilters.add("fr");
		langFilters.add("zh");
		assertEquals(langFilters, config.getFilterLanguages());

		assertEquals(2, config.getActions().size());
		assertTrue(config.getActions().get(0) instanceof RdfSerializationAction);
		assertTrue(config.getActions().get(1) instanceof JsonSerializationAction);
		RdfSerializationAction rdfAction = (RdfSerializationAction) config
				.getActions().get(0);
		JsonSerializationAction jsonAction = (JsonSerializationAction) config
				.getActions().get(1);

		assertTrue(rdfAction.useStdOut);
		assertEquals(DumpProcessingOutputAction.COMPRESS_GZIP,
				rdfAction.compressionType);
		assertEquals("/tmp/wikidata-items.nt", rdfAction.outputDestination);
		assertEquals(RdfSerializer.TASK_ITEMS | RdfSerializer.TASK_STATEMENTS
				| RdfSerializer.TASK_TERMS, rdfAction.tasks);

		assertFalse(jsonAction.useStdOut);
		assertEquals(DumpProcessingOutputAction.COMPRESS_BZ2,
				jsonAction.compressionType);
		assertEquals("/tmp/wikidata-dump.json", jsonAction.outputDestination);
	}

}