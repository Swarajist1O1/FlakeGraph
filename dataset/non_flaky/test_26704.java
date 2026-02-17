class DummyClass_26704 {
	@Test
	public void testIsComplete()  {
		Pair subject = new Pair(Arrays.asList(new Developer("dev1"), new Developer("dev2")));
		
		assertThat(subject.isComplete(), is(true));
	}

}