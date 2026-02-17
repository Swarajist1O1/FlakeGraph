class DummyClass_110108 {
	@Test
	public void testInsertDumpInformation() {
		DumpProcessingOutputAction action = new JsonSerializationAction();
		action.setDumpInformation("wikidata", "20150131");
		String result = action
				.insertDumpInformation("{PROJECT}-{DATE}-dump.json");
		assertEquals(result, "wikidata-20150131-dump.json");
	}

}