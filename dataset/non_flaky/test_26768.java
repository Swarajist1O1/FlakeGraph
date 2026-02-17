class DummyClass_26768 {
	@Test
	public void testHashCode() {
		DayPairs pairsOfToday = new DayPairs();
		DayPairs differentPairsOfToday = new DayPairs();
		DayPairs yesterdayPairs = new DayPairs();
		yesterdayPairs.setDate(getYesterdayDate());
		Pair pair1 = new Pair(Arrays.asList(new Developer("dev1"), new Developer("dev2")));
		Pair pair2 = new Pair(Arrays.asList(new Developer("dev3"), new Developer("dev4")));
		pairsOfToday.addPair("track1", pair1);
		differentPairsOfToday.addPair("track2", pair2);
		yesterdayPairs.addPair("track1", pair1);
		
		assertThat(pairsOfToday.hashCode(), is(equalTo(differentPairsOfToday.hashCode())));
		assertThat(yesterdayPairs.hashCode(), is(not(equalTo(pairsOfToday.hashCode()))));
	}

}