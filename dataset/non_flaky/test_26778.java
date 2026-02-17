class DummyClass_26778 {
	@Test
	public void testGetCompanyOriginalName() {
		assertThat(new Company("Company").getOriginalName(), is("Company"));
	}

}