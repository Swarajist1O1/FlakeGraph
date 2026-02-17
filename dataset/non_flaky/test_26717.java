class DummyClass_26717 {
	@Test
	public void testIsOpsPairFalse()  {
		Pair subject = new Pair();
		
		subject.setOpsPair(false);
		
		assertThat(subject.isOpsPair(), is(false));
	}

}