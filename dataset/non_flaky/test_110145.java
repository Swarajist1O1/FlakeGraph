class DummyClass_110145 {
	@Test
	public void testSiteLinkFilterArguments() {
		String[] args = new String[] { "--fSite", "fawiki,dewiki" };
		ClientConfiguration config = new ClientConfiguration(args);

		Set<String> siteFilters = new HashSet<>();
		siteFilters.add("fawiki");
		siteFilters.add("dewiki");

		assertEquals(siteFilters, config.getFilterSiteKeys());
	}

}