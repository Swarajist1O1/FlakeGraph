class DummyClass_110146 {
	@Test
	public void testSiteLinkFilterArgumentsEmpty() {
		String[] args = new String[] { "--fSite", "-" };
		ClientConfiguration config = new ClientConfiguration(args);

		Set<String> siteFilters = new HashSet<>();

		assertEquals(siteFilters, config.getFilterSiteKeys());
	}

}