class DummyClass_26780 {
	@Test
	public void testGetCompanyExperiencedDevs() {
		Developer developerCompanyA = new Developer("a");
		developerCompanyA.setCompany(new Company("a"));
		Developer newDeveloperCompanyA = new Developer("a");
		newDeveloperCompanyA.setCompany(new Company("a"));
		newDeveloperCompanyA.setNew(true);
		Developer developerCompanyB = new Developer("b");
		developerCompanyB.setCompany(new Company("b"));
		
		List<Developer> companyDevs = new Company("a").getCompanyExperiencedDevs(Arrays.asList(developerCompanyA, developerCompanyB, newDeveloperCompanyA));
		
		assertThat(companyDevs.size(), is(1));
		assertThat(companyDevs.get(0), is(developerCompanyA));
	}

}