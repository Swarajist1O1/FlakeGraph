class DummyClass_26736 {
	@Test
	public void testCompanyDefault() {
		Developer developer = new Developer("developerId");
		
		assertThat(developer.getCompany().getName(), is(""));
	}

}