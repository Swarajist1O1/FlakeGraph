class DummyClass_26750 {
	@Test
	public void testGetPairsReturnOnlyDevPairs() {
		List<DayPairs> pairsListFromDevs = getPairsListFromDevs(getStandardDevs());
		Pair opsPair = pairsListFromDevs.get(0).getPairByTrack("track1");
		opsPair.setOpsPair(true);
		
		List<Pair> pairs = new DevPairCombinations(pairsListFromDevs).getPairs();
		
		assertThat(pairs.size(), is(5));
		for (Pair pair : pairs) {
			assertThat(pair.isOpsPair(), is(false));
		}
	}

}