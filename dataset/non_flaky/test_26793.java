class DummyClass_26793 {
	@Test
	public void testGenerateNewDayPairsWithEverydayRotation() {
		PairCombinations pairs = getPairsList();
		List<Developer> devs = getStandardDevs();
		List<String> tracks = Arrays.asList("track1", "track2", "track3");
		Map<Pair, Integer> pairsWeight = subject.buildPairsWeightFromPastPairing(pairs, devs);
		subject.buildDevelopersPairingDays(pairs, devs);
		
		DayPairsHelper subjectWithEverydayRotation = new DayPairsHelper(trelloPairsRepository, true);
		DayPairs dayPairs = subjectWithEverydayRotation.generateNewDayPairs(tracks, devs, pairs, pairsWeight, getStandardCompanies());

		assertThat(dayPairs.getTracks().size(), is(2));
		assertThat(dayPairs.getTracks(), containsInAnyOrder("track1", "track2"));
		assertThat(dayPairs.getPairByTrack("track1"),
				is(not(new Pair(Arrays.asList(new Developer("dev1"), new Developer("dev2"))))));
		assertThat(dayPairs.getPairByTrack("track2"),
				is(not(new Pair(Arrays.asList(new Developer("dev3"), new Developer("dev4"))))));
		
		boolean trackOneHasDev2 = dayPairs.getPairByTrack("track1").getFirstDev().equals(new Developer("dev2")) || dayPairs.getPairByTrack("track1").getSecondDev().equals(new Developer("dev2"));
		boolean trackTwoHasDev4 = dayPairs.getPairByTrack("track2").getFirstDev().equals(new Developer("dev4")) || dayPairs.getPairByTrack("track2").getSecondDev().equals(new Developer("dev4"));
		assertThat(trackOneHasDev2, is(true));
		assertThat(trackTwoHasDev4, is(true));
	}

}