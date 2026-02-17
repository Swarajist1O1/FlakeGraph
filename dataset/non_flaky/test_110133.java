class DummyClass_110133 {
	@Test
	public void testDumpLocationArgumentsShort() {
		String[] args = new String[] { "-d", "dumps/wikidata/" };
		ClientConfiguration config = new ClientConfiguration(args);
		assertEquals("dumps/wikidata/", config.getDumpDirectoryLocation());
	}

}