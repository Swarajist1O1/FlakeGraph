class DummyClass_110134 {
	@Test
	public void testDumpLocationArgumentsLong() {
		String[] args = new String[] { "--dumps", "dumps/wikidata/" };
		ClientConfiguration config = new ClientConfiguration(args);
		assertEquals("dumps/wikidata/", config.getDumpDirectoryLocation());
	}

}