class DummyClass_110183 {
	@Test
	public void testGetMissingPropertyType() {
		assertNull(this.propertyRegister.getPropertyType(dataObjectFactory
				.getPropertyIdValue("P10", this.siteIri)));
		// Check twice to test fast failing on retry
		assertNull(this.propertyRegister.getPropertyType(dataObjectFactory
				.getPropertyIdValue("P10", this.siteIri)));
	}

}