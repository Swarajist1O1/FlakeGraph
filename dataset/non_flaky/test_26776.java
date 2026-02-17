class DummyClass_26776 {
	@Test
	public void testGetCompanyNameWithSaces() {
		assertThat(new Company("  company  ").getName(), is("company"));
	}

}