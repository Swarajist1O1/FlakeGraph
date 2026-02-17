class DummyClass_26713 {
	@Test
	public void testIsBuildPairFalse()  {
		Pair subject = new Pair();
		
		subject.setBuildPair(false);
		
		assertThat(subject.isBuildPair(), is(false));
	}

}