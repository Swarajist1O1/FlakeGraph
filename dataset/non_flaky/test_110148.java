class DummyClass_110148 {
	@Test
	public void testPropertyFilterArgumentsEmpty() {
		String[] args = new String[] { "--fProp", "-" };
		ClientConfiguration config = new ClientConfiguration(args);

		Set<PropertyIdValue> propFilters = new HashSet<>();

		assertEquals(propFilters, config.getFilterProperties());
	}

}