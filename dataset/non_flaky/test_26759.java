class DummyClass_26759 {
	@Test
	public void testIsRotationTimeForNewDevUnconformPair() {
		List<Developer> standardDevs = getStandardDevs();
		standardDevs.stream().forEach(developer -> developer.setNew(true));
		List<DayPairs> pastPairs = getPairsListFromDevs(standardDevs);
		pastPairs.remove(2);
		pastPairs.remove(1);		
		DevPairCombinations devPairCombinations = new DevPairCombinations(pastPairs);
		
		
		assertThat(devPairCombinations.isRotationTime(Arrays.asList("track1", "track2"), standardDevs, false), is(true));
	}

}