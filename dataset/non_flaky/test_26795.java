class DummyClass_26795 {
	@Test
	public void testGenerateNewDayPairsSoloRequired() {
		PairCombinations pairs = getPairsList();
		List<Developer> devs = Arrays.asList(new Developer("dev1"), new Developer("dev2"), new Developer("dev3"));
		List<String> tracks = Arrays.asList("track1", "track2", "track3");
		Map<Pair, Integer> pairsWeight = subject.buildPairsWeightFromPastPairing(pairs, devs);

		DayPairs dayPairs = subject.generateNewDayPairs(tracks, devs, pairs, pairsWeight, getStandardCompanies());

		assertThat(dayPairs.getTracks().size(), is(2));
		assertThat(dayPairs.getTracks(), containsInAnyOrder("track1", "track2"));
		assertThat(dayPairs.getPairByTrack("track1"),
				is((new Pair(Arrays.asList(new Developer("dev1"), new Developer("dev2"))))));
	}

}