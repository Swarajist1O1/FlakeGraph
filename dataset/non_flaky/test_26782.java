class DummyClass_26782 {
	@Test
	public void testGetCompanyTracksNoHit() {
		List<String> tracks = Arrays.asList("other-company-track", "third-track");
		
		String companyTrack = new Company("Company").getCompanyTrack(tracks);
		
		assertThat(companyTrack, is(nullValue()));
	}

}