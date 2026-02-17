class DummyClass_110197 {
	@Test
	public void testWriteSiteLinks() throws RDFHandlerException, IOException,
			RDFParseException {
		this.sites.setSiteInformation("enwiki", "wikipedia", "en", "mediawiki",
				"http://en.wikipedia.org/w/$1",
				"http://en.wikipedia.org/wiki/$1");
		this.sites.setSiteInformation("dewiki", "wikipedia", "de", "mediawiki",
				"http://de.wikipedia.org/w/$1",
				"http://de.wikipedia.org/wiki/$1");
		Map<String, SiteLink> siteLinks = objectFactory.createSiteLinks();
		this.rdfConverter.writeSiteLinks(this.resource, siteLinks);
		this.rdfWriter.finish();
		Model model = RdfTestHelpers.parseRdf(out.toString());
		assertEquals(model, RdfTestHelpers.parseRdf(RdfTestHelpers
				.getResourceFromFile("SiteLinks.rdf")));

	}

}