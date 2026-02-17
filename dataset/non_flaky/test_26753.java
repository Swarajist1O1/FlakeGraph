class DummyClass_26753 {
	@Test
	public void testGetPastPairsForMissingHistory() {
		DevPairCombinations devPairCombinations = new DevPairCombinations(getPairsListFromDevs(getStandardDevs()));
		
		
		assertThat(devPairCombinations.getPastPairs(3), is(nullValue()));
	}

}