class DummyClass_26771 {
	@Test
	public void testHasPair() {
		DayPairs pairs = new DayPairs();
		Pair pair = new Pair(Arrays.asList(new Developer("dev1"), new Developer("dev2")));
		Pair differentPair = new Pair();
		pairs.addPair("track", pair);
		
		assertThat(pairs.hasPair(pair), is(true));
		assertThat(pairs.hasPair(differentPair), is(false));
	}

}