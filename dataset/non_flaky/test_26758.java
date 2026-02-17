class DummyClass_26758 {
	@Test
	public void testIsRotationTimeForTwoDayPair() {
		List<Developer> standardDevs = getStandardDevs();
		DevPairCombinations devPairCombinations = new DevPairCombinations(getPairsListFromDevs(standardDevs));
		
		
		assertThat(devPairCombinations.isRotationTime(Arrays.asList("track1", "track2"), standardDevs, false), is(true));
	}

}