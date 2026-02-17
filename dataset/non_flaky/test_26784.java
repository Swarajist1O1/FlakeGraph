class DummyClass_26784 {
	@Test
	public void testIsCompanyTrackFalse() {
		boolean isCompanyTrack = new Company("Company").isCompanyTrack("companyB-track");

		assertThat(isCompanyTrack, is(false));
	}

}