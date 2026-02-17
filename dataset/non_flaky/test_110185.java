class DummyClass_110185 {
	@Test
	public void testSetPropertyTypeFromStringValue() {
		assertEquals(this.propertyRegister.setPropertyTypeFromStringValue(
				dataObjectFactory.getPropertyIdValue("P434", this.siteIri),
				dataObjectFactory
						.getStringValue("http://musicbrainz.org/$1/artist")),
				"http://wikiba.se/ontology#String");
	}

}