class DummyClass_26760 {
	@Test
	public void testIsRotationForOneDayPair() {
		List<Developer> standardDevs = getStandardDevs();
		List<DayPairs> pastPairs = getPairsListFromDevs(standardDevs);
		pastPairs.remove(2);
		pastPairs.remove(1);
		DevPairCombinations devPairCombinations = new DevPairCombinations(pastPairs);
		
		
		assertThat(devPairCombinations.isRotationTime(Arrays.asList("track1", "track2"), standardDevs, false), is(false));
	}

}