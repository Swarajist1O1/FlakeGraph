class DummyClass_26711 {
	@Test
	public void testEqualDifferentPairs()  {
		Pair subject = new Pair(Arrays.asList(new Developer("dev1")));
		Pair subject2 = new Pair(Arrays.asList(new Developer("dev2")));
		
		assertThat(subject.equals(subject2), is(false));
	}

}