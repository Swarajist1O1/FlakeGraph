class DummyClass_110186 {
	@Test
	public void testSetMissingPropertyTypeFromStringValue() {
		assertEquals(this.propertyRegister.setPropertyTypeFromStringValue(
				dataObjectFactory.getPropertyIdValue("P10", this.siteIri),
				dataObjectFactory
						.getStringValue("http://musicbrainz.org/$1/artist")),
				"http://wikiba.se/ontology#String");
	}

}