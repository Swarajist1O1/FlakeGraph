class DummyClass_26773 {
	@Test
	public void testGetTrackByPair() {
		DayPairs pairs = new DayPairs();
		Pair pair = new Pair(Arrays.asList(new Developer("dev1"), new Developer("dev2")));
		Pair differentPair = new Pair();
		pairs.addPair("track", pair);
		
		assertThat(pairs.getTrackByPair(pair), is(equalTo("track")));
		assertThat(pairs.getTrackByPair(differentPair), is(nullValue()));
	}

}