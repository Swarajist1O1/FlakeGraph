class DummyClass_26761 {
	@Test
	public void testIsRotationForOneDayPairWithEveryDayRotation() {
		List<Developer> standardDevs = getStandardDevs();
		List<DayPairs> pastPairs = getPairsListFromDevs(standardDevs);
		pastPairs.remove(2);
		pastPairs.remove(1);
		DevPairCombinations devPairCombinations = new DevPairCombinations(pastPairs);
		
		
		assertThat(devPairCombinations.isRotationTime(Arrays.asList("track1", "track2"), standardDevs, true), is(true));
	}

}