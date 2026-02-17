class DummyClass_26756 {
	@Test
	public void testGetPastPairByTrackForMissingHistory() {
		DevPairCombinations devPairCombinations = new DevPairCombinations(getPairsListFromDevs(getStandardDevs()));
		
		
		assertThat(devPairCombinations.getPastPairByTrack(3, "track1"), is(nullValue()));
	}

}