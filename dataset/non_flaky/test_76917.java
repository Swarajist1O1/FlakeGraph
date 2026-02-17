class DummyClass_76917 {
	@Test // #55
	public void considersRevisionNumberSortOrder() {

		Country de = new Country();
		de.code = "de";
		de.name = "Deutschland";

		countryRepository.save(de);

		de.name = "Germany";

		countryRepository.save(de);

		Page<Revision<Integer, Country>> page = countryRepository.findRevisions(de.id,
				PageRequest.of(0, 10, RevisionSort.desc()));

		assertThat(page).hasSize(2);
		assertThat(page.getContent().get(0).getRequiredRevisionNumber())
				.isGreaterThan(page.getContent().get(1).getRequiredRevisionNumber());
	}

}