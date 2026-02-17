class DummyClass_110120 {
	@Test
	public void testRdfOutput() {
		String[] args = { "-a", "rdf", "-o", "output/wikidata.rdf" };
		ClientConfiguration configuration = new ClientConfiguration(args);
		DumpProcessingAction action = configuration.actions.get(0);
		action.open();
		action.close();
		assertTrue(action
				.getReport()
				.matches(
						"Finished serialization of \\d+ RDF triples in file output/wikidata.rdf"));
	}

}