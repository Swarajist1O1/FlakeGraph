class DummyClass_26772 {
	@Test
	public void testReplacePairWith() {
		DayPairs pairs = new DayPairs();
		Pair pair = new Pair(Arrays.asList(new Developer("dev1"), new Developer("dev2")));
		Pair differentPair = new Pair();
		pairs.addPair("track", pair);
		
		assertThat(pairs.hasPair(pair), is(true));
		assertThat(pairs.hasPair(differentPair), is(false));
		
		pairs.replacePairWith(pair, differentPair);
		
		assertThat(pairs.hasPair(pair), is(false));
		assertThat(pairs.hasPair(differentPair), is(true));
	}

}