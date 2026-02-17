class DummyClass_26777 {
	@Test
	public void testGetCompanyNameWithUpperCase() {
		assertThat(new Company("COMPANY").getName(), is("company"));
	}

}