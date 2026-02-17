class DummyClass_26737 {
	@Test
	public void testCompany() {
		Developer developer = new Developer("developerId");
		developer.setCompany(new Company("my-company"));
		
		assertThat(developer.getCompany().getName(), is("my-company"));
	}

}