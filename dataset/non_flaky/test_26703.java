class DummyClass_26703 {
	@Test
	public void testOtherDevWithOneDev()  {
		Pair subject = new Pair(Arrays.asList(new Developer("dev1")));
		
		assertThat(subject.getOtherDev(new Developer("dev1")), nullValue());
	}

}