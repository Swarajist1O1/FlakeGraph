class DummyClass_76915 {
	@Test // #1
	public void returnsEmptyRevisionsForUnrevisionedEntity() {
		assertThat(countryRepository.findRevisions(100L)).isEmpty();
	}

}