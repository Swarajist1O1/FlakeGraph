class DummyClass_26712 {
	@Test
	public void testIsSolo()  {
		Pair subject = new Pair(Arrays.asList(new Developer("dev1")));
		Pair subject2 = new Pair(Arrays.asList(new Developer("dev1"), new Developer("dev2")));
		
		assertThat(subject.equals(subject2), is(false));
	}

}