class DummyClass_26697 {
	@Test
	public void testGetAndSetDevs()  {
		Pair subject = new Pair();
		subject.setDevs(Arrays.asList(new Developer("dev1"), new Developer("dev2")));
		
		assertThat(subject.getDevs(), is(equalTo(Arrays.asList(new Developer("dev1"), new Developer("dev2")))));
	}

}