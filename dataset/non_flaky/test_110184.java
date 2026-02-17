class DummyClass_110184 {
	@Test
	public void testSetPropertyTypeFromEntityIdValue() {
		assertEquals(this.propertyRegister.setPropertyTypeFromEntityIdValue(
				this.dataObjectFactory
						.getPropertyIdValue("P1001", this.siteIri),
				this.dataObjectFactory.getItemIdValue("Q20", this.siteIri)),
				DatatypeIdValue.DT_ITEM);
	}

}