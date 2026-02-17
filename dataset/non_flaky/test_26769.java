class DummyClass_26769 {
	@Test
	public void testEquals() {
		DayPairs pairsOfToday = new DayPairs();
		DayPairs differentPairsOfToday = new DayPairs();
		DayPairs yesterdayPairs = new DayPairs();
		yesterdayPairs.setDate(getYesterdayDate());
		Pair pair1 = new Pair(Arrays.asList(new Developer("dev1"), new Developer("dev2")));
		Pair pair2 = new Pair(Arrays.asList(new Developer("dev3"), new Developer("dev4")));
		pairsOfToday.addPair("track1", pair1);
		differentPairsOfToday.addPair("track2", pair2);
		yesterdayPairs.addPair("track1", pair1);
		
		assertThat(pairsOfToday, is(equalTo(differentPairsOfToday)));
		assertThat(yesterdayPairs, is(not(equalTo(pairsOfToday))));
	}

}