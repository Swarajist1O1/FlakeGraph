class DummyClass_26734 {
	@Test
	public void testIsRotationForDifferentWeekPairs() {
		List<Developer> standardDevs = getStandardDevs();
		DayPairs pairs = new DayPairs();
		pairs.setDate(getDateWeeksBefore(1));
		pairs.addPair("track1", new Pair(Arrays.asList(standardDevs.get(0), standardDevs.get(1)), true, "track1"));
		
		OpsPairCombinations devPairCombinations = new OpsPairCombinations(Arrays.asList(pairs));
		Company company = new Company("myCompany");
		company.setDevOpsRotationStrategy("weekly");
		devPairCombinations.setCompany(company);

		assertThat(devPairCombinations.isRotationTime(Arrays.asList("track1"), standardDevs, false), is(true));
	}

}