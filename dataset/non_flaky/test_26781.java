class DummyClass_26781 {
	@Test
	public void testGetCompanyTracks() {
		List<String> tracks = Arrays.asList("other-company-track", "company-track", "companyB-track");
		
		String companyTrack = new Company("Company").getCompanyTrack(tracks);
		
		assertThat(companyTrack, is("company-track"));
	}

}