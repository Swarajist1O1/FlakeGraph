class DummyClass_26754 {
	@Test
	public void testGetPastPairByTrack() {
		List<Developer> standardDevs = getStandardDevs();
		DevPairCombinations devPairCombinations = new DevPairCombinations(getPairsListFromDevs(standardDevs));
		
		
		assertThat(devPairCombinations.getPastPairByTrack(0, "track1"), is(getPairsListFromDevs(standardDevs).get(0).getPairByTrack("track1")));
		assertThat(devPairCombinations.getPastPairByTrack(1, "track2"), is(getPairsListFromDevs(standardDevs).get(1).getPairByTrack("track2")));
		assertThat(devPairCombinations.getPastPairByTrack(2, "track1"), is(getPairsListFromDevs(standardDevs).get(2).getPairByTrack("track1")));
	}

}