class DummyClass_26716 {
	@Test
	public void testIsOpsPairTrue()  {
		Pair subject = new Pair();
		
		subject.setOpsPair(true);
		
		assertThat(subject.isOpsPair(), is(true));
	}

}