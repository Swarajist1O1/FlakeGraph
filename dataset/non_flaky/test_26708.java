class DummyClass_26708 {
	@Test
	public void testHashCodeNotEqual()  {
		Pair subject = new Pair(Arrays.asList(new Developer("dev1")));
		Pair subject2 = new Pair(Arrays.asList(new Developer("dev2")));
		
		assertThat(subject.hashCode(), is(not(equalTo(subject2.hashCode()))));
	}

}