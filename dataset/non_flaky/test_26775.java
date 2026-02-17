class DummyClass_26775 {
	@Test
	public void testGetCompanyName() {
		assertThat(new Company("company").getName(), is("company"));
	}

}