class DummyClass_26727 {
	@Test
	public void testGetPastPairByTrackForMissingHistory() {
		OpsPairCombinations devPairCombinations = new OpsPairCombinations(getPairsListFromDevs(getStandardDevs()));
		
		
		assertThat(devPairCombinations.getPastPairByTrack(3, "track1"), is(nullValue()));
	}

}