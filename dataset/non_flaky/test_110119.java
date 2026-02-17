class DummyClass_110119 {
	@Test
	public void testJsonOutput() {
		String[] args = { "-a", "json", "-o", "output/wikidata.json" };
		ClientConfiguration configuration = new ClientConfiguration(args);
		DumpProcessingAction action = configuration.actions.get(0);
		action.open();
		action.close();
		assertTrue(action
				.getReport()
				.matches(
						"Finished serialization of \\d+ EntityDocuments in file output/wikidata.json"));
	}

}