class DummyClass_26731 {
	@Test
	public void testIsRotationTimeForEmptyHistoryWithEveryDayRotationAnNoWeeklyRotation() {
		OpsPairCombinations devPairCombinations = new OpsPairCombinations(new ArrayList<>());

		assertThat(devPairCombinations.isRotationTime(Arrays.asList("track1"), getStandardDevs(), true), is(true));
	}

}