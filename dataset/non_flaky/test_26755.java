class DummyClass_26755 {
	@Test(expected =  RuntimeException.class)
	public void testGetPastPairByTrackThrowsRuntimeErrorForOpsPair() {
		List<DayPairs> pairsListFromDevs = getPairsListFromDevs(getStandardDevs());
		Pair opsPair = pairsListFromDevs.get(0).getPairByTrack("track1");
		opsPair.setOpsPair(true);
		DevPairCombinations devPairCombinations = new DevPairCombinations(pairsListFromDevs);
		
		
		assertThat(devPairCombinations.getPastPairByTrack(0, "track1"), is(Arrays.asList(getPairsListFromDevs(getStandardDevs()).get(0).getPairByTrack("track1"))));
	}

}