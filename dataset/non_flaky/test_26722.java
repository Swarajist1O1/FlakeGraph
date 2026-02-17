class DummyClass_26722 {
	@Test
	public void testGetPastPairs() {
		List<Developer> standardDevs = getStandardDevs();
		OpsPairCombinations devPairCombinations = new OpsPairCombinations(getPairsListFromDevs(standardDevs));
		
		
		assertThat(devPairCombinations.getPastPairs(0), is(getPairsListFromDevs(standardDevs).get(0).getPairs().values().stream().collect(Collectors.toList())));
		assertThat(devPairCombinations.getPastPairs(1), is(getPairsListFromDevs(standardDevs).get(1).getPairs().values().stream().collect(Collectors.toList())));
		assertThat(devPairCombinations.getPastPairs(2), is(getPairsListFromDevs(standardDevs).get(2).getPairs().values().stream().collect(Collectors.toList())));
	}

}