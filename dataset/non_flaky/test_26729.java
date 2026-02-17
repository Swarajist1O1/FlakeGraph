class DummyClass_26729 {
	@Test
	public void testIsRotationTimeForEmptyHistory() {
		OpsPairCombinations devPairCombinations = new OpsPairCombinations(new ArrayList<>());
		Company company = new Company("myCompany");
		company.setDevOpsRotationStrategy("weekly");
		devPairCombinations.setCompany(company);

		assertThat(devPairCombinations.isRotationTime(Arrays.asList("track1"), getStandardDevs(), false), is(false));
	}

}