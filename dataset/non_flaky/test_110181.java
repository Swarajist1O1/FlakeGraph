class DummyClass_110181 {
	@Test
	public void testFetchPropertyUriPattern() {
		PropertyIdValue pid = this.dataObjectFactory.getPropertyIdValue("P434",
				this.siteIri);
		assertEquals("http://musicbrainz.org/$1/artist",
				this.propertyRegister.getPropertyUriPattern(pid));
		// Check twice to test that the cached retrieval works too
		assertEquals("http://musicbrainz.org/$1/artist",
				this.propertyRegister.getPropertyUriPattern(pid));
		assertEquals(50,
				this.propertyRegister.smallestUnfetchedPropertyIdNumber);
		assertTrue(this.propertyRegister.datatypes.keySet().contains("P434"));
	}

}