class DummyClass_26706 {
	@Test
	public void testToString()  {
		Pair subject = new Pair(Arrays.asList(new Developer("dev1")));
		
		assertThat(subject.toString(), is(equalTo("Pair [devs=[dev1], opsPair=false, locked=false]")));
	}

}