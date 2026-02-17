class DummyClass_26783 {
	@Test
	public void testIsCompanyTrack() {
		boolean isCompanyTrack = new Company("Company").isCompanyTrack("company-track");
		
		assertThat(isCompanyTrack, is(true));
	}

}