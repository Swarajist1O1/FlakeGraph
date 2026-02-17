class DummyClass_26720 {
	@Test
	public void testTrackSet() {
		Pair subject = new Pair();
		
		subject.setTrack("track");
		
		assertThat(subject.getTrack(), is("track"));
	}

}