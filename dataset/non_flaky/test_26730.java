class DummyClass_26730 {
	@Test
	public void testIsRotationTimeForEmptyHistoryWithEveryDayRotation() {
		OpsPairCombinations devPairCombinations = new OpsPairCombinations(new ArrayList<>());
		Company company = new Company("myCompany");
		company.setDevOpsRotationStrategy("weekly");
		devPairCombinations.setCompany(company);

		assertThat(devPairCombinations.isRotationTime(Arrays.asList("track1"), getStandardDevs(), true), is(false));
	}

}