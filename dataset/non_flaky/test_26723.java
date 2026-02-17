class DummyClass_26723 {
	@Test
	public void testGetPastPairsFiltersOps() {
		List<DayPairs> pairsListFromDevs = getPairsListFromDevs(getStandardDevs(), false);
		Pair opsPair = pairsListFromDevs.get(0).getPairByTrack("track1");
		opsPair.setOpsPair(true);
		OpsPairCombinations devPairCombinations = new OpsPairCombinations(pairsListFromDevs);
		
		
		assertThat(devPairCombinations.getPastPairs(0), is(Arrays.asList(pairsListFromDevs.get(0).getPairByTrack("track1"))));
	}

}