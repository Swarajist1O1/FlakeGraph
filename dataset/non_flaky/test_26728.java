class DummyClass_26728 {
	@Test
	public void testGetPastPairByTrackForMissingTrack() {
		OpsPairCombinations devPairCombinations = new OpsPairCombinations(getPairsListFromDevs(getStandardDevs()));
		
		
		assertThat(devPairCombinations.getPastPairByTrack(1, "track5"), is(nullValue()));
	}

}