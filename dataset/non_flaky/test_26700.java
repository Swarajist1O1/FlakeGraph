class DummyClass_26700 {
	@Test
	public void testAddDevWithNull()  {
		Pair subject = new Pair();
		
		subject.addDev(null);
		
		assertThat(subject.getDevs().isEmpty(), is(true));
	}

}