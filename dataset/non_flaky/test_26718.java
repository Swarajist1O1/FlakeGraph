class DummyClass_26718 {
	@Test
	public void testIsCommunityPairTrue()  {
		Pair subject = new Pair();
		
		subject.setCommunityPair(true);
		
		assertThat(subject.isCommunityPair(), is(true));
	}

}