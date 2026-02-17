class DummyClass_26724 {
	@Test
	public void testGetPastPairsForMissingHistory() {
		OpsPairCombinations devPairCombinations = new OpsPairCombinations(getPairsListFromDevs(getStandardDevs()));
		
		
		assertThat(devPairCombinations.getPastPairs(3), is(nullValue()));
	}

}