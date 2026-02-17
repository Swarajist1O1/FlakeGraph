class DummyClass_26701 {
	@Test
	public void testHasDev()  {
		Pair subject = new Pair(Arrays.asList(new Developer("dev1")));
		
		assertThat(subject.hasDev(new Developer("dev1")), is(true));
	}

}