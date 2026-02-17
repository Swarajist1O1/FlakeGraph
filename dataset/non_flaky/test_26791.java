class DummyClass_26791 {
	@Test
	public void testAdaptPairsWeightForNewDevelopers() {

		Developer developer1 = new Developer("dev1");
		developer1.setNew(true);
		Developer developer2 = new Developer("dev2");
		developer2.setNew(true);
		Developer developer3 = new Developer("dev3");
		Developer developer4 = new Developer("dev4");
		List<Developer> devs = Arrays.asList(developer1, developer2, developer3, developer4);
		PairCombinations pairs = new DevPairCombinations(getPairsListFromDevs(devs));

		Map<Pair, Integer> pairsWeight = subject.buildPairsWeightFromPastPairing(pairs, devs);
		subject.adaptPairsWeight(pairsWeight, devs);

		assertThat(pairsWeight.get(new Pair(Arrays.asList(new Developer("dev1"), new Developer("dev2")))), is(102));
		assertThat(pairsWeight.get(new Pair(Arrays.asList(new Developer("dev1"), new Developer("dev3")))), is(0));
		assertThat(pairsWeight.get(new Pair(Arrays.asList(new Developer("dev1"), new Developer("dev4")))), is(1));
		assertThat(pairsWeight.get(new Pair(Arrays.asList(new Developer("dev2"), new Developer("dev3")))), is(1));
		assertThat(pairsWeight.get(new Pair(Arrays.asList(new Developer("dev2"), new Developer("dev4")))), is(0));
		assertThat(pairsWeight.get(new Pair(Arrays.asList(new Developer("dev3"), new Developer("dev4")))), is(2));
	}

}