class DummyClass_26699 {
	@Test
	public void testAddDev()  {
		Pair subject = new Pair(Arrays.asList(new Developer("dev1")));
		
		assertThat(subject.getDevs(), is(equalTo(Arrays.asList(new Developer("dev1")))));
	}

}