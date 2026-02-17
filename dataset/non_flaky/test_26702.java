class DummyClass_26702 {
	@Test
	public void testGetOtherDev()  {
		Pair subject = new Pair(Arrays.asList(new Developer("dev1"), new Developer("dev2")));
		
		assertThat(subject.getOtherDev(new Developer("dev1")), is(equalTo(new Developer("dev2"))));
	}

}