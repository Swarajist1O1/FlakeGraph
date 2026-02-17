class DummyClass_110144 {
	@Test
	public void testLanguageFilterArgumentsEmpty() {
		String[] args = new String[] { "--fLang", "-" };
		ClientConfiguration config = new ClientConfiguration(args);

		Set<String> langFilters = new HashSet<>();

		assertEquals(langFilters, config.getFilterLanguages());
	}

}