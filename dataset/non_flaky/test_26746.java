class DummyClass_26746 {
	@Test
	public void testGetTrackWeightDefault() {
		Developer developer = new Developer("developerId");
		
		assertThat(developer.getTrackWeight("track"), is(0));
	}

}