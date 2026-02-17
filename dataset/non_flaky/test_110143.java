class DummyClass_110143 {
	@Test
	public void testLanguageFilterArguments() {
		String[] args = new String[] { "--fLang", "en,de" };
		ClientConfiguration config = new ClientConfiguration(args);

		Set<String> langFilters = new HashSet<>();
		langFilters.add("en");
		langFilters.add("de");

		assertEquals(langFilters, config.getFilterLanguages());
	}

}