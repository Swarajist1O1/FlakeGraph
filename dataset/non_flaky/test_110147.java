class DummyClass_110147 {
	@Test
	public void testPropertyFilterArguments() {
		String[] args = new String[] { "--fProp", "P100,P31" };
		ClientConfiguration config = new ClientConfiguration(args);

		Set<PropertyIdValue> propFilters = new HashSet<>();
		propFilters.add(Datamodel.makeWikidataPropertyIdValue("P31"));
		propFilters.add(Datamodel.makeWikidataPropertyIdValue("P100"));

		assertEquals(propFilters, config.getFilterProperties());
	}

}