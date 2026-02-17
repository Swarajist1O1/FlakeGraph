class DummyClass_76918 {
	@Test // #21
	public void findsDeletedRevisions() {

		Country de = new Country();
		de.code = "de";
		de.name = "Deutschland";

		countryRepository.save(de);

		countryRepository.delete(de);

		Revisions<Integer, Country> revisions = countryRepository.findRevisions(de.id);

		assertThat(revisions).hasSize(2);
		assertThat(revisions.getLatestRevision().getEntity()) //
				.isNotNull() //
				.extracting(c -> c.name, c -> c.code) //
				.containsExactly(null, null);
	}

}