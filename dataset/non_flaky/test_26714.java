class DummyClass_26714 {
	@Test
	public void testIsBuildPairTrue()  {
		Pair subject = new Pair();
		
		subject.setBuildPair(true);
		
		assertThat(subject.isBuildPair(), is(true));
	}

}