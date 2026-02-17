class DummyClass_26767 {
	@Test
	public void testGetPairByTrack() {
		Pair pair1 = new Pair(Arrays.asList(new Developer("dev1"), new Developer("dev2")));
		Pair pair2 = new Pair(Arrays.asList(new Developer("dev3"), new Developer("dev4")));
		DayPairs pairs = new DayPairs();
		pairs.addPair("track1", pair1);
		pairs.addPair("track2", pair2);
		
		assertThat(pairs.getPairByTrack("track1"), is(equalTo(pair1)));
		assertThat(pairs.getPairByTrack("track2"), is(equalTo(pair2)));
	}

}