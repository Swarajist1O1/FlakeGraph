class DummyClass_26709 {
	@Test
	public void testEqual()  {
		Pair subject = new Pair(Arrays.asList(new Developer("dev1")));
		Pair subject2 = new Pair(Arrays.asList(new Developer("dev1")));
		
		assertThat(subject.equals(subject2), is(true));
	}

}