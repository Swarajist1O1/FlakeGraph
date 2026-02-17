class DummyClass_26787 {
	@Test
	public void testGetCompanyDevs() {
		Developer developerCompanyA = new Developer("a");
		developerCompanyA.setCompany(new Company("a"));
		Developer newDeveloperCompanyA = new Developer("a");
		newDeveloperCompanyA.setCompany(new Company("a"));
		newDeveloperCompanyA.setNew(true);
		Developer developerCompanyB = new Developer("b");
		developerCompanyB.setCompany(new Company("b"));

		List<Developer> companyDevs = new Company("a").getDevs(Arrays.asList(developerCompanyA, developerCompanyB, newDeveloperCompanyA));

		assertThat(companyDevs, is(Arrays.asList(developerCompanyA, newDeveloperCompanyA)));
	}

}