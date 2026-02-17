class DummyClass_26794 {
	@Test
	public void testGenerateNewDayPairsWithSmallestWeight() {
		PairCombinations pairs = getLongPairsList();
		List<Developer> devs = Arrays.asList(new Developer("dev1"), new Developer("dev2"), new Developer("dev3"),
				new Developer("dev4"), new Developer("dev5"), new Developer("dev6"));
		List<String> tracks = Arrays.asList("track1", "track2", "track3");
		Map<Pair, Integer> pairsWeight = subject.buildPairsWeightFromPastPairing(pairs, devs);
		subject.buildDevelopersPairingDays(pairs, devs);
		
		DayPairs dayPairs = subject.generateNewDayPairs(tracks, devs, pairs, pairsWeight, getStandardCompanies());

		assertThat(dayPairs.getTracks().size(), is(3));
		assertThat(dayPairs.getTracks(), containsInAnyOrder("track1", "track2", "track3"));
		System.out.println(dayPairs.getPairs());
		assertThat(dayPairs.hasPair(new Pair(Arrays.asList(new Developer("dev1"), new Developer("dev6")))), is(true));
		assertThat(dayPairs.hasPair(new Pair(Arrays.asList(new Developer("dev3"), new Developer("dev2")))), is(true));
		assertThat(dayPairs.hasPair(new Pair(Arrays.asList(new Developer("dev5"), new Developer("dev4")))), is(true));
	}

}