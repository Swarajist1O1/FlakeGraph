class DummyClass_26707 {
	@Test
	public void testHashCode()  {
		Pair subject = new Pair(Arrays.asList(new Developer("dev1")));
		Pair subject2 = new Pair(Arrays.asList(new Developer("dev1")));
		
		assertThat(subject.hashCode(), is(equalTo(subject2.hashCode())));
	}

}