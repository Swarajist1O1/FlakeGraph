class DummyClass_26757 {
	@Test
	public void testGetPastPairByTrackForMissingTrack() {
		DevPairCombinations devPairCombinations = new DevPairCombinations(getPairsListFromDevs(getStandardDevs()));
		
		
		assertThat(devPairCombinations.getPastPairByTrack(1, "track4"), is(nullValue()));
	}

}