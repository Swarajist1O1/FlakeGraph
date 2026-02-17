class DummyClass_26705 {
	@Test
	public void testIsCompleteWithOneDev()  {
		Pair subject = new Pair(Arrays.asList(new Developer("dev1")));
		
		assertThat(subject.isComplete(), is(false));
	}

}