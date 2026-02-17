class DummyClass_26790 {
	@Test
	public void testBuildPairsWeightFromPastPairing() {
		PairCombinations pairs = getPairsList();
		List<Developer> devs = getStandardDevs();

		Map<Pair, Integer> pairsWeight = subject.buildPairsWeightFromPastPairing(pairs, devs);

		assertThat(pairsWeight.get(new Pair(Arrays.asList(new Developer("dev1"), new Developer("dev2")))), is(2));
		assertThat(pairsWeight.get(new Pair(Arrays.asList(new Developer("dev1"), new Developer("dev3")))), is(0));
		assertThat(pairsWeight.get(new Pair(Arrays.asList(new Developer("dev1"), new Developer("dev4")))), is(1));
		assertThat(pairsWeight.get(new Pair(Arrays.asList(new Developer("dev2"), new Developer("dev3")))), is(1));
		assertThat(pairsWeight.get(new Pair(Arrays.asList(new Developer("dev2"), new Developer("dev4")))), is(0));
		assertThat(pairsWeight.get(new Pair(Arrays.asList(new Developer("dev3"), new Developer("dev4")))), is(2));
	}

}