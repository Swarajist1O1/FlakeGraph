class DummyClass_26715 {
	@Test
	public void testIsCommunitydPairFalse()  {
		Pair subject = new Pair();
		
		subject.setCommunityPair(false);
		
		assertThat(subject.isCommunityPair(), is(false));
	}

}