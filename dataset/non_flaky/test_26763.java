class DummyClass_26763 {
	@Test
	public void testAddPairAndGetTracks(){
		HashMap<String, Pair> expectedPairs = new HashMap<String, Pair>();
		expectedPairs.put("testTrack", new Pair(Arrays.asList(new Developer("dev1"), new Developer("dev2"))));
		DayPairs pairs = new DayPairs();
		pairs.addPair("testTrack", expectedPairs.get("testTrack"));
		
		assertThat(pairs.getPairs(), is(equalTo(expectedPairs)));
		assertThat(pairs.getTracks(), is(equalTo(new HashSet<>(Arrays.asList("testTrack")))));
	}

}