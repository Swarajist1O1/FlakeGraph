class DummyClass_110182 {
	@Test
	public void testGetPropertyType() {
		assertEquals(DatatypeIdValue.DT_STRING,
				this.propertyRegister.getPropertyType(dataObjectFactory
						.getPropertyIdValue("P434", this.siteIri)));
		// Check twice to test that the cached retrieval works too
		assertEquals(DatatypeIdValue.DT_STRING,
				this.propertyRegister.getPropertyType(dataObjectFactory
						.getPropertyIdValue("P434", this.siteIri)));
		assertEquals(50,
				this.propertyRegister.smallestUnfetchedPropertyIdNumber);
		assertTrue(this.propertyRegister.datatypes.keySet().contains("P434"));
	}

}