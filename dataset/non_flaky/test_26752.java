class DummyClass_26752 {
	@Test
	public void testGetPastPairsFiltersOps() {
		List<DayPairs> pairsListFromDevs = getPairsListFromDevs(getStandardDevs());
		Pair opsPair = pairsListFromDevs.get(0).getPairByTrack("track1");
		opsPair.setOpsPair(true);
		DevPairCombinations devPairCombinations = new DevPairCombinations(pairsListFromDevs);
		
		
		assertThat(devPairCombinations.getPastPairs(0), is(Arrays.asList(pairsListFromDevs.get(0).getPairByTrack("track2"))));
	}

}