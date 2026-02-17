class DummyClass_26747 {
	@Test
	public void testGetTrackWeightOne() {
		Developer developer = new Developer("developerId");
		
		developer.updateTrackWeight("track");
		
		assertThat(developer.getTrackWeight("track"), is(1));
	}

}