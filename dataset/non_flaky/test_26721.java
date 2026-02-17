class DummyClass_26721 {
	@Test
	public void testGetPairsReturnOnlyDevPairs() {
		List<DayPairs> pairsListFromDevs = getPairsListFromDevs(getStandardDevs(), false);
		Pair opsPair = pairsListFromDevs.get(0).getPairByTrack("track1");
		opsPair.setOpsPair(true);
		
		List<Pair> pairs = new OpsPairCombinations(pairsListFromDevs).getPairs();
		
		assertThat(pairs.contains(opsPair), is(true));
		assertThat(pairs.size(), is(1));
	}

}