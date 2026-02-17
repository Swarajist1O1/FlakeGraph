class DummyClass_110187 {
	@Test
	public void testWikidataPropertyRegister() {
		PropertyRegister pr = PropertyRegister.getWikidataPropertyRegister();
		assertEquals(Datamodel.SITE_WIKIDATA, pr.getUriPrefix());
		assertEquals("P1921", pr.uriPatternPropertyId);
	}

}