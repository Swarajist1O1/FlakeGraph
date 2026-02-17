class DummyClass_26698 {
	@Test
	public void testGetAndSetDevsWithNullValues()  {
		Pair subject = new Pair();
		subject.setDevs(Arrays.asList(null, new Developer("dev2")));
		
		assertThat(subject.getDevs(), is(equalTo(Arrays.asList(new Developer("dev2")))));
	}

}