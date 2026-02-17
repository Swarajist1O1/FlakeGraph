class DummyClass_26770 {
	@Test
	public void testToString() {
		DayPairs pairs = new DayPairs();
		Pair pair = new Pair(Arrays.asList(new Developer("dev1"), new Developer("dev2")));
		pairs.addPair("track", pair);
		
		assertThat(pairs.toString(), is(equalTo("Pairs [pairs=" + pairs.getPairs() + ", date=" + pairs.format(pairs.getDate()) + "]")));
	}

}