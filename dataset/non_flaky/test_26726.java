class DummyClass_26726 {
	@Test(expected =  RuntimeException.class)
	public void testGetPastPairByTrackThrowsRuntimeErrorForOpsPair() {
		List<DayPairs> pairsListFromDevs = getPairsListFromDevs(getStandardDevs(), false);
		Pair opsPair = pairsListFromDevs.get(0).getPairByTrack("track1");
		opsPair.setOpsPair(true);
		OpsPairCombinations devPairCombinations = new OpsPairCombinations(pairsListFromDevs);
		
		
		devPairCombinations.getPastPairByTrack(0, "track2");
	}

}