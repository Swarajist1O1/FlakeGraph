class DummyClass_26710 {
	@Test
	public void testEqualWithOpsTrue()  {
		Pair subject = new Pair(Arrays.asList(new Developer("dev1")));
		subject.setOpsPair(true);
		Pair subject2 = new Pair(Arrays.asList(new Developer("dev1")));
		subject2.setOpsPair(true);
		
		assertThat(subject.equals(subject2), is(true));
	}

}